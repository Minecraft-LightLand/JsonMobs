package dev.xkmc.ymlparser.primitive.calc;

record OpenRange(Op op, double val) implements IRange {

	public static IRange of(Op op, double val) {
		return new OpenRange(op, val);
	}

	@Override
	public boolean test(double val) {
		return op.compare.test(val, this.val());
	}

	enum Op {
		LT((a, b) -> a < b),
		LEQ((a, b) -> a <= b),
		GT((a, b) -> a > b),
		GEQ((a, b) -> a >= b),
		EQ((a, b) -> a == b);

		private final Compare compare;

		Op(Compare compare) {
			this.compare = compare;
		}

	}

	private interface Compare {

		boolean test(double num, double range);

	}

}
