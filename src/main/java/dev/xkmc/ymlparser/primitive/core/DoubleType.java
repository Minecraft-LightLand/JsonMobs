package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;

public class DoubleType extends NumericType<Double> {

	public DoubleType(String name) {
		super(name);
	}

	@Override
	public Double parseStatic(ParserLogger logger, StringElement.ListElem elem) {
		String str = elem.toString();
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			logger.error(elem.start, "failed to parse " + str + " as double. Default to 0");
			return 0d;
		}
	}

	@Override
	public Double cast(double val) {
		return val;
	}

	@Override
	protected DataHolder<Double> toStatic(double val) {
		return new StaticData<>(val);
	}

	@Override
	protected DataHolder<Double> toRange(double min, double max) {
		return new DoubleRange(min, max);
	}

	private record DoubleRange(double min, double max) implements DataHolder<Double> {

		@Override
		public Double get(DataContext meta) {
			return meta.getRandom().nextDouble() * (max - min) + min;
		}

	}

}
