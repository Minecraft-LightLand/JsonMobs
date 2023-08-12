package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;

@ConditionType(
		author = "jaylawl",
		type = EvaluationType.ENTITY,
		name = "hasgravity",
		aliases = {"gravity"},
		description = "Tests if the target has gravity"
)
public class HasGravityCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext target) {
		return !target.get().isNoGravity();
	}

}
