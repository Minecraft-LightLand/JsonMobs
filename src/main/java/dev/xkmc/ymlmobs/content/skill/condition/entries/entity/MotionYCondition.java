package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = {"Ashijin", "lcy0x1"},
		name = "motiony",
		aliases = {"moty", "vy"},
		description = "Checks the Y motion of the target entity against a range."
)
public class MotionYCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "velocity",
			aliases = {"v"},
			description = "The velocity to check for"
	)
	public IRange velocity;

	@Argument(name = "absolute", aliases = "abs", description = "Check only absolute value for velocity", optional = true)
	public boolean absolute = true;

	@Override
	public boolean check(EntityDataContext entity) {
		double y = entity.get().getDeltaMovement().y;
		return velocity.test(absolute ? Math.abs(y) : y);
	}

}
