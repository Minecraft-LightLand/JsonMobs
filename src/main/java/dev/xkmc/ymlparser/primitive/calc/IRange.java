package dev.xkmc.ymlparser.primitive.calc;

public interface IRange {

	IRange NULL = new Any();
	IRange POSITIVE = OpenRange.of(OpenRange.Op.GT, 0);

	boolean test(double val);

	record Any() implements IRange {

		@Override
		public boolean test(double val) {
			return true;
		}
	}

}
