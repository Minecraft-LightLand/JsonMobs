package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;

public class IntType extends NumericType<Integer> {

	public IntType(String name) {
		super(name);
	}

	@Override
	public Integer parseStatic(ParserLogger logger, StringElement.ListElem elem) {
		String str = elem.toString();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			logger.error(elem.start, "failed to parse " + str + " as int. Default to 0");
			return 0;
		}
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
