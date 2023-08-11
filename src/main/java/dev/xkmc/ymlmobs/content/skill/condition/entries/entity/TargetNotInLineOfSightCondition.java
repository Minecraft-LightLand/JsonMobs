package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "targetNotInLineOfSight",
		description = "Tests if the target doesn't have line of sight to their target"
)
public class TargetNotInLineOfSightCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return !(entity instanceof Mob mob && mob.getTarget() != null && entity.hasLineOfSight(mob.getTarget()));
	}
}
