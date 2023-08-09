package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.HealthModifier;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

public class HealthModifierType implements DataType<HealthModifier> {

	@Override
	public String name() {
		return null;
	}

	@Override
	public HealthModifier parse(ParserLogger logger, StringElement.ListElem elem) {
		return null;
	}

}
