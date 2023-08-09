package dev.xkmc.ymlparser.primitive.calc;

import net.objecthunter.exp4j.function.Function;

public class Functions {
	public static final Function min = new Function("min", 2) {
		public double apply(double... doubles) {
			return Math.min(doubles[0], doubles[1]);
		}
	};
	public static final Function max = new Function("max", 2) {
		public double apply(double... doubles) {
			return Math.max(doubles[0], doubles[1]);
		}
	};
	public static final Function atan2 = new Function("atan2", 2) {
		public double apply(double... doubles) {
			return Math.atan2(doubles[0], doubles[1]);
		}
	};
	public static final Function random = new Function("random", 2) {
		public double apply(double... doubles) {
			double min = doubles[0];
			double max = doubles[1];
			return min + Math.random() * (max - min);
		}
	};
	public static final Function[] functions;

	public Functions() {
	}

	static {
		functions = new Function[]{min, max, atan2, random};
	}
}
