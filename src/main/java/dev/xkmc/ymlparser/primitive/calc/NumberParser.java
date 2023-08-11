package dev.xkmc.ymlparser.primitive.calc;

import com.mojang.datafixers.util.Either;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.regex.Pattern;

public class NumberParser {

	private static final Pattern PATTERN_BASIC = Pattern.compile("[+-]?[0-9.]+");
	private static final Pattern PATTERN_SCIENTIFIC = Pattern.compile("[+-]?[0-9.]+e[+-]?[0-9]+");

	private static final Pattern PATTERN_RANGE_NATURAL = Pattern.compile("[+-]?[0-9.]+-[0-9.]+");
	private static final Pattern PATTERN_RANGE_TO = Pattern.compile("[0-9e.+-]+to[0-9e.+-]+");
	private static final Pattern PATTERN_RANGE_TILDA = Pattern.compile("[0-9e.+-]+~[0-9e.+-]+");

	public static Optional<Either<Double, CloseRange>> parseNumber(String str) {
		if (PATTERN_BASIC.matcher(str).matches() || PATTERN_SCIENTIFIC.matcher(str).matches()) {
			try {
				return Optional.of(Either.left(Double.parseDouble(str)));
			} catch (NumberFormatException e) {
				return Optional.empty();
			}
		} else {
			String s0, s1;
			if (PATTERN_RANGE_NATURAL.matcher(str).matches()) {
				String[] strs = str.split("-");
				if (strs.length == 2) {
					s0 = strs[0];
					s1 = strs[1];
				} else if (strs.length == 3 && strs[0].length() == 0) {
					s0 = strs[1];
					s1 = strs[2];
				} else {
					return Optional.empty();
				}
			} else if (PATTERN_RANGE_TO.matcher(str).matches()) {
				String[] strs = str.split("to");
				if (strs.length == 2) {
					s0 = strs[0];
					s1 = strs[1];
				} else {
					return Optional.empty();
				}
			} else if (PATTERN_RANGE_TILDA.matcher(str).matches()) {
				String[] strs = str.split("~");
				if (strs.length == 2) {
					s0 = strs[0];
					s1 = strs[1];
				} else {
					return Optional.empty();
				}
			} else {
				return Optional.empty();
			}
			double d0, d1;
			try {
				d0 = Double.parseDouble(s0);
				d1 = Double.parseDouble(s1);
				return Optional.of(Either.right(new CloseRange(Math.min(d0, d1), Math.max(d0, d1))));
			} catch (NumberFormatException e) {
				return Optional.empty();
			}
		}
	}

	@Nullable
	public static IRange parseRange(String str) {
		try {
			if (str.startsWith(">=")) {
				return OpenRange.of(OpenRange.Op.GEQ, Double.parseDouble(str.substring(2)));
			} else if (str.startsWith("<=")) {
				return OpenRange.of(OpenRange.Op.LEQ, Double.parseDouble(str.substring(2)));
			} else if (str.startsWith(">")) {
				return OpenRange.of(OpenRange.Op.GT, Double.parseDouble(str.substring(1)));
			} else if (str.startsWith("<")) {
				return OpenRange.of(OpenRange.Op.LT, Double.parseDouble(str.substring(1)));
			} else if (str.startsWith("=")) {
				return OpenRange.of(OpenRange.Op.EQ, Double.parseDouble(str.substring(1)));
			}
		} catch (NumberFormatException e) {
			return null;
		}
		var result = parseNumber(str);
		return result.map(e -> e.map(l -> OpenRange.of(OpenRange.Op.EQ, l), r -> r)).orElse(null);
	}

}
