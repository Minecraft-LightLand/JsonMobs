package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionAction;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.MetaDataType;

import java.util.List;

public class ConditionInsType extends MetaDataType<ConditionInstance, SkillCondition> {

	protected ConditionInsType() {
		super(YMDataTypeRegistry.CONDITION);
	}

	@Override
	protected ConditionInstance createSimple(SkillCondition type) {
		return ConditionInstance.of(type, true);
	}

	@Override
	protected ConditionInstance createWithParams(ParserLogger logger, SkillCondition type, List<StringElement.ListElem> other) {
		if (other.size() == 0) {
			return createSimple(type);
		}
		if (other.size() >= 2) {
			logger.error(other.get(1).start, "More than one param found for condition. Ignoring");
		}
		ConditionAction bool = YMDataTypeRegistry.CONDITION_ACTION.parse(logger, other.get(0));
		return ConditionInstance.of(type, bool);
	}

}
