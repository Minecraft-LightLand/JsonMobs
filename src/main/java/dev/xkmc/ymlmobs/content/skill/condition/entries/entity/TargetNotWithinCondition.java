package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Mob;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "targetnotwithin",
		description = "Tests if the target's target is not within a certain distance"
)
public class TargetNotWithinCondition extends SkillCondition implements IEntityCondition {

	@Argument(name = "distance", aliases = {"d"}, description = "The distance to test")
	public double distance;

	@Override
	public boolean check(EntityDataContext entity) {
		return !(entity.get() instanceof Mob mob && mob.getTarget() != null && mob.distanceTo(mob.getTarget()) <= distance);
	}

}
