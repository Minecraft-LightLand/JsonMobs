package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;

@ConditionType(
		author = "Ashijin",
		type = EvaluationType.ENTITY,
		name = "haspassenger",
		description = "If the target entity has a passenger"
)
public class HasPassengerCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return !e.get().getPassengers().isEmpty();
	}

}
