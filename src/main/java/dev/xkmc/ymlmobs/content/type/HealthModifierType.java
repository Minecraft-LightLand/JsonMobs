package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.skill.core.HealthModifier;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

public record HealthModifierType(String name) implements DataType<HealthModifier> {

	private static double parseSub(String str, int start, int end) {
		return Double.parseDouble(str.substring(start, str.length() - start - end));
	}

	@Override
	public HealthModifier parse(ParserLogger logger, StringElement.ListElem elem) {
		//TODO multi-part health mod
		String str = elem.toString();
		try {
			if (str.startsWith("<=")) {
				if (str.endsWith("%")) {
					double val = parseSub(str, 2, 1);
					return new HealthModifier(e -> e.php() <= val);
				} else {
					double val = parseSub(str, 2, 0);
					return new HealthModifier(e -> e.hp() <= val);
				}
			} else if (str.startsWith(">=")) {
				if (str.endsWith("%")) {
					double val = parseSub(str, 2, 1);
					return new HealthModifier(e -> e.php() >= val);
				} else {
					double val = parseSub(str, 2, 0);
					return new HealthModifier(e -> e.hp() >= val);
				}
			} else if (str.startsWith("<")) {
				if (str.endsWith("%")) {
					double val = parseSub(str, 1, 1);
					return new HealthModifier(e -> e.php() < val);
				} else {
					double val = parseSub(str, 1, 0);
					return new HealthModifier(e -> e.hp() < val);
				}
			} else if (str.startsWith(">")) {
				if (str.endsWith("%")) {
					double val = parseSub(str, 1, 1);
					return new HealthModifier(e -> e.php() > val);
				} else {
					double val = parseSub(str, 1, 0);
					return new HealthModifier(e -> e.hp() > val);
				}
			} else if (str.startsWith("=")) {
				if (str.indexOf('-') >= 0) {
					String[] strs = str.split("-");
					if (strs.length == 2) {
						boolean p0 = strs[0].endsWith("%");
						boolean p1 = strs[1].endsWith("%");
						if (p0 && p1) {
							double v0 = parseSub(strs[0], 0, 1);
							double v1 = parseSub(strs[1], 0, 1);
							return new HealthModifier(e -> e.php() >= v0 && e.php() <= v1);
						} else if (!p0 && !p1) {
							double v0 = parseSub(strs[0], 0, 0);
							double v1 = parseSub(strs[1], 0, 0);
							return new HealthModifier(e -> e.hp() >= v0 && e.hp() <= v1);
						}
					}
				}
			}
		} catch (Exception ignored) {

		}
		throw logger.fatal(elem.start, "Illegal health modifier " + str);
	}

	public boolean isValidStart(StringElement.ListElem elem) {
		return elem.startsWith("<") || elem.startsWith(">") || elem.startsWith("=");
	}

}
