package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "Pine",
		value = EvaluationType.ENTITY,
		name = "isBaby",
		description = "If the target is a baby"
)
public class IsBabyCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return entity instanceof AgeableMob mob && mob.isBaby();
	}
}
