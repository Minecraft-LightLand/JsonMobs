package dev.xkmc.ymlparser.primitive.calc;

import net.objecthunter.exp4j.operator.Operator;

public final class Operators {

	public static final Operator LT = new Operator("<", 2, true, 499) {
		public double apply(double[] values) {
			return values[0] < values[1] ? 1.0D : 0.0D;
		}
	};

	public static final Operator LEQ = new Operator("<=", 2, true, 499) {
		public double apply(double[] values) {
			return values[0] <= values[1] ? 1.0D : 0.0D;
		}
	};

	public static final Operator GT = new Operator(">", 2, true, 499) {
		public double apply(double[] values) {
			return values[0] > values[1] ? 1.0D : 0.0D;
		}
	};

	public static final Operator GEQ = new Operator(">=", 2, true, 499) {
		public double apply(double[] values) {
			return values[0] >= values[1] ? 1.0D : 0.0D;
		}
	};

	public static final Operator EQ = new Operator("==", 2, true, 499) {
		public double apply(double[] values) {
			return values[0] == values[1] ? 1.0D : 0.0D;
		}
	};

	public static final Operator[] OPERATORS = new Operator[]{LT, LEQ, GT, GEQ, EQ};

}