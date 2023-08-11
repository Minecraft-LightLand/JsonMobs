package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "targetWithin",
		description = "Tests if the target's target is within a certain distance"
)
public class TargetWithinCondition extends SkillCondition implements IEntityCondition {

	@Argument(name = "distance", aliases = {"d"}, description = "Distance to check")
	public double distance = 2;

	@Override
	public boolean check(LivingEntity entity) {
		return entity instanceof Mob mob && mob.getTarget() != null && mob.distanceTo(mob.getTarget()) <= distance;
	}
}