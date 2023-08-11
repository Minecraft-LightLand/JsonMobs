package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "velocity",
		description = "Checks the velocity of the target entity against a range."
)
public class VelocityCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "velocity",
			aliases = {"v"},
			description = "The velocity to check for"
	)
	public IRange velocity;

	@Override
	public boolean check(LivingEntity e) {
		return velocity.test(e.getDeltaMovement().length());
	}
}