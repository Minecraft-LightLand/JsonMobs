package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.AgeableMob;

@ConditionType(
		author = "Pine",
		type = EvaluationType.ENTITY,
		name = "isBaby",
		description = "If the target is a baby"
)
public class IsBabyCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		return entity.get() instanceof AgeableMob mob && mob.isBaby();
	}
}
