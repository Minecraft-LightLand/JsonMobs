package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "isInSurvivalMode",
		aliases = {"inSurvivalMode"},
		description = "If the target is in survival mode, or in other words, can be damaged"
)
public class InSurvivalModeCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		return !entity.get().isInvulnerable();
	}

}
