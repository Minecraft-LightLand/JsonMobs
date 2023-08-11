package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.primitive.calc.*;
import dev.xkmc.ymlparser.primitive.variable.NumericVariable;
import dev.xkmc.ymlparser.primitive.variable.VariableContext;
import dev.xkmc.ymlparser.type.HolderDataTypeImpl;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class NumericType<T> extends HolderDataTypeImpl<T> {

	@Nullable
	private static <T> DataHolder<T> parseSimple(NumericType<T> type, String str) {
		var result = NumberParser.parseNumber(str);
		return result.map(e -> e.map(type::toStatic, r -> type.toRange(r.min(), r.max()))).orElse(null);
	}

	public NumericType(String name) {
		super(name);
	}

	@Override
	public DataHolder<T> parse(ParserLogger logger, StringElement.ListElem elem) {
		elem = elem.tryUnwrap(StringHierarchy.Type.STRING);
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.StrElem str) {
			String num = str.toString();
			DataHolder<T> ans = parseSimple(this, num);
			if (ans != null) return ans;
		}
		StringBuilder builder = new StringBuilder();
		Map<String, NumericVariable> vars = new LinkedHashMap<>();
		for (var e : elem.list) {
			if (e instanceof StringElement.Hierarchy hier) {
				if (hier.hierarchy == StringHierarchy.ARROW) {
					NumericVariable holder = VariableContext.ofNumeric(hier.list);
					if (holder == null) {
						logger.error(hier.start, "Invalid variable " + hier + ", replaced with 0");
						holder = x -> 0;
					}
					String name = "p" + vars.size();
					vars.put(name, holder);
				} else {
					throw logger.fatal(hier.start, "Invalid structure " + hier + " as number");
				}
			} else {
				builder.append(e.toString());
			}
		}
		Expression exp;
		ContextRef ref = new ContextRef();
		try {
			exp = new ExpressionBuilder(builder.toString())
					.operator(Operators.OPERATORS)
					.functions(Functions.getFunctions(ref))
					.variables(vars.keySet())
					.build();
		} catch (IllegalArgumentException e) {
			logger.error(elem.start, e.getMessage());
			throw e;
		}
		return new NumericExpressionHolder<>(this, exp, vars, ref);
	}

	public abstract T cast(double evaluate);

	protected abstract DataHolder<T> toStatic(double val);

	protected abstract DataHolder<T> toRange(double min, double max);

}
