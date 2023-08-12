package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Joshinn",
		name = "sprinting",
		aliases = {"issprinting"},
		description = "Whether or not the target entity is sprinting. Works on all mobs."
)
public class SprintingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get().isSprinting();
	}
}
