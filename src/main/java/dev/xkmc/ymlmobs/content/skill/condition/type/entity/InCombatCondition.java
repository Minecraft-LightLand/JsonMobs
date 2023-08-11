package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@ConditionType(
		author = "Ashijin",
		value = EvaluationType.ENTITY,
		name = "incombat",
		description = "If the target mob is considered in combat"
)
public class InCombatCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		// TODO if (am.hasThreatTable()) return am.getThreatTable().inCombat();
		return entity instanceof Mob mob && mob.getTarget() != null;
	}
}
