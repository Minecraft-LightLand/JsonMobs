package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "onGround",
		aliases = {"grounded"},
		description = "If the target entity is standing on solid ground"
)
public class OnGroundCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get().onGround();
	}

}
