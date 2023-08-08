package dev.xkmc.ymlparser.primitive;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.type.DataType;
import io.lumine.mythic.core.utils.math.Functions;
import io.lumine.mythic.core.utils.math.Operators;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class NumericType<T> implements DataType<DataHolder<T>> {

	public static final DataType<DataHolder<Integer>> INT = new NumericType<>("int", d -> (int) Math.round(d));
	public static final DataType<DataHolder<Double>> DOUBLE = new NumericType<>("double", d -> d);

	private final String name;
	private final Function<Double, T> func;

	public NumericType(String name, Function<Double, T> func) {
		this.name = name;
		this.func = func;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public DataHolder<T> parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.Hierarchy hier) {
			if (hier.hierarchy.type == StringHierarchy.Type.STRING) {
				if (hier.list.size() == 1) {
					elem = hier.list.get(0);
				}
			}
		}
		StringBuilder builder = new StringBuilder();
		Map<String, VariableHolder> vars = new LinkedHashMap<>();
		for (var e : elem.list) {
			if (e instanceof StringElement.Hierarchy hier) {
				if (hier.hierarchy == StringHierarchy.A) {

					//TODO fill function
				} else {
					logger.fatal(hier.start, "Invalid structure " + hier + " as number");
				}
			} else {
				builder.append(e.toString());
			}
		}
		var exp = new ExpressionBuilder(builder.toString())
				.operator(Operators.operators)
				.functions(Functions.functions)
				.variables(vars.keySet())
				.build();
		return new NumericHolder<>(this, exp, vars);
	}

	private T cast(double evaluate) {
		return func.apply(evaluate);
	}

	public record NumericHolder<T>(NumericType<T> type, Expression exp,
								   Map<String, VariableHolder> vars) implements DataHolder<T> {

		@Override
		public T get(DataContext meta) {
			for (var e : vars.entrySet()) {
				exp.setVariable(e.getKey(), e.getValue().parse(meta));
			}
			return type.cast(exp.evaluate());
		}
	}

}
