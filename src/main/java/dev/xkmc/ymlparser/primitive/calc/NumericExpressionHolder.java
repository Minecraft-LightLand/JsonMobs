package dev.xkmc.ymlparser.primitive.calc;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.core.NumericType;
import dev.xkmc.ymlparser.primitive.variable.NumericVariable;
import net.objecthunter.exp4j.Expression;

import java.util.Map;

public record NumericExpressionHolder<T>(NumericType<T> type, Expression exp,
										 Map<String, NumericVariable> vars) implements DataHolder<T> {

	@Override
	public T get(DataContext meta) {
		for (var e : vars.entrySet()) {
			exp.setVariable(e.getKey(), e.getValue().parse(meta));
		}
		return type.cast(exp.evaluate());
	}
}
