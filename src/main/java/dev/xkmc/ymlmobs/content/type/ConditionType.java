package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.MetaCondition;
import dev.xkmc.ymlmobs.content.core.SkillCondition;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.MetaDataType;

import java.util.List;

public class ConditionType extends MetaDataType<MetaCondition, SkillCondition> {

	protected ConditionType() {
		super(YMDataTypeRegistry.CONDITION);
	}

	@Override
	protected MetaCondition createSimple(SkillCondition type) {
		return null;//TODO
	}

	@Override
	protected MetaCondition createWithParams(ParserLogger logger, SkillCondition type, List<StringElement.ListElem> other) {
		return null;//TODO
	}
}
