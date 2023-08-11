package dev.xkmc.ymlparser.primitive.calc;

public record CloseRange(double min, double max) implements IRange {
	@Override
	public boolean test(double val) {
		return val >= min && val <= max;
	}
}
