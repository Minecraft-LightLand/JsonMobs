package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "mounted",
		description = "If the target entity is riding a mount/vehicle"
)
public class MountedCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get().getVehicle() != null;
	}

}
