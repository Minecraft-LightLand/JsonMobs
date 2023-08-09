package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.SkillCondition;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.MetaDataType;

public class ConditionType implements MetaDataType<SkillCondition> {
	@Override
	public String name() {
		return null;
	}

	@Override
	public SkillCondition parse(ParserLogger logger, StringElement.ListElem elem) {
		return null;
	}
}
