package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.Mob;

@ConditionType(
		author = "Ashijin",
		type = EvaluationType.ENTITY,
		name = "incombat",
		description = "If the target mob is considered in combat"
)
public class InCombatCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		// TODO if (am.hasThreatTable()) return am.getThreatTable().inCombat();
		return entity.get() instanceof Mob mob && mob.getTarget() != null;
	}
}
