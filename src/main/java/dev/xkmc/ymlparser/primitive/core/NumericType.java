package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.primitive.calc.Functions;
import dev.xkmc.ymlparser.primitive.calc.NumericExpressionHolder;
import dev.xkmc.ymlparser.primitive.calc.Operators;
import dev.xkmc.ymlparser.primitive.variable.NumericVariable;
import dev.xkmc.ymlparser.primitive.variable.VariableContext;
import dev.xkmc.ymlparser.type.DataType;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class NumericType<T> implements DataType<DataHolder<T>> {

	private static final String PATTERN_NUM = "[0-9e.+-]+";
	private static final String PATTERN_RANGE = "[0-9e.+-]+to[0-9e.+-]+";

	@Nullable
	private static <T> DataHolder<T> parseSimple(NumericType<T> type, String str) {
		if (str.matches(PATTERN_NUM)) {
			try {
				double val = Double.parseDouble(str);
				return type.toStatic(val);
			} catch (NumberFormatException e) {
				return null;
			}
		} else if (str.matches(PATTERN_RANGE)) {
			String[] strs = str.split("to");
			if (strs.length == 2) {
				double d0, d1;
				try {
					d0 = Double.parseDouble(strs[0]);
					d1 = Double.parseDouble(strs[1]);
					return type.toRange(Math.min(d0, d1), Math.max(d0, d1));
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		return null;
	}

	private final String name;

	public NumericType(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
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
				if (hier.hierarchy == StringHierarchy.A) {
					NumericVariable holder = VariableContext.ofNumeric(hier.list);
					if (holder == null) {
						logger.error(hier.start, "Invalid variable " + hier + ", replaced with 0");
						holder = x -> 0;
					}
					String name = "p" + vars.size();
					vars.put(name, holder);
				} else {
					logger.fatal(hier.start, "Invalid structure " + hier + " as number");
				}
			} else {
				builder.append(e.toString());
			}
		}
		Expression exp;
		try {
			exp = new ExpressionBuilder(builder.toString())
					.operator(Operators.operators)
					.functions(Functions.functions)
					.variables(vars.keySet())
					.build();
		} catch (IllegalArgumentException e) {
			logger.error(elem.start, e.getMessage());
			throw e;
		}
		return new NumericExpressionHolder<>(this, exp, vars);
	}

	public abstract T cast(double evaluate);

	protected abstract DataHolder<T> toStatic(double val);

	protected abstract DataHolder<T> toRange(double min, double max);

}
