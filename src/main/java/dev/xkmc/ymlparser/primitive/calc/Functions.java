package dev.xkmc.ymlparser.primitive.calc;

import net.objecthunter.exp4j.function.Function;

public class Functions {
	public static final Function MIN = new Function("min", 2) {
		public double apply(double... doubles) {
			return Math.min(doubles[0], doubles[1]);
		}
	};

	public static final Function MAX = new Function("max", 2) {
		public double apply(double... doubles) {
			return Math.max(doubles[0], doubles[1]);
		}
	};

	public static final Function ATAN2 = new Function("atan2", 2) {
		public double apply(double... doubles) {
			return Math.atan2(doubles[0], doubles[1]);
		}
	};

	public static Function getRandom(ContextRef ctx) {
		return new Function("random", 2) {
			public double apply(double... doubles) {
				double min = doubles[0];
				double max = doubles[1];
				return min + ctx.getData().getRandom().nextDouble() * (max - min);
			}
		};
	}

	public static Function[] getFunctions(ContextRef ctx) {
		return new Function[]{MIN, MAX, ATAN2, getRandom(ctx)};
	}


}
