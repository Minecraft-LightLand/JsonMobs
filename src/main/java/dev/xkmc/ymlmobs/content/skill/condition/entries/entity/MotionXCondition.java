package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = {"Ashijin", "lcy0x1"},
		name = "motionx",
		aliases = {"motx", "vx"},
		description = "Checks the X motion of the target entity against a range."
)
public class MotionXCondition extends SkillCondition implements IEntityCondition {

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
		double x = entity.get().getDeltaMovement().x;
		return velocity.test(absolute ? Math.abs(x) : x);
	}

}
