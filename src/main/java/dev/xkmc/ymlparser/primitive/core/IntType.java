package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;

public class IntType extends NumericType<Integer> {

	public IntType(String name) {
		super(name);
	}

	@Override
	public Integer cast(double val) {
		return (int) Math.round(val);
	}

	@Override
	protected DataHolder<Integer> toStatic(double val) {
		return new StaticData<>(cast(val));
	}

	@Override
	protected DataHolder<Integer> toRange(double min, double max) {
		return new IntRange(cast(min), cast(max));
	}

	private record IntRange(int min, int max) implements DataHolder<Integer> {

		@Override
		public Integer get(DataContext meta) {
			return meta.getRandom().nextIntBetweenInclusive(min, max);
		}

	}

}
