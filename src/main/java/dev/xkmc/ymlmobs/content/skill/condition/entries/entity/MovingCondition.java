package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "moving",
		aliases = {"ismoving"},
		description = "If the target has a velocity greater than zero"
)
public class MovingCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "exact",
			aliases = {"e"},
			description = "Sets whether to check if the velocities are exactly the same. 1 block per second is the threshold for approximately not moving",
			optional = true
	)
	public boolean exact = true;

	@Override
	public boolean check(EntityDataContext entity) {
		return entity.get().getDeltaMovement().length() > (exact ? 1e-3 : 0.05);
	}
}
