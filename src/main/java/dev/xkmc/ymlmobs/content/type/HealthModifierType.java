package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.HealthModifier;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

public class HealthModifierType implements DataType<HealthModifier> {

	private final String name;

	public HealthModifierType(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public HealthModifier parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.startsWith("<=")) {

		} else if (elem.startsWith(">=")) {

		} else if (elem.startsWith("<")) {

		} else if (elem.startsWith(">")) {

		} else if (elem.startsWith("=")) {

		}
		throw logger.fatal(elem.start, "Illegal health modidifer " + elem);
	}

	public boolean isValidStart(StringElement.ListElem elem) {
		return elem.startsWith("<") || elem.startsWith(">") || elem.startsWith("=");
	}

	private enum Operation {
		LS, LEQ, GT, GEQ, EQ, RANGE
	}

}
