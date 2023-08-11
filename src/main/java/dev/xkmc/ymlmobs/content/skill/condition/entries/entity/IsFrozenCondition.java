package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "Phil",
		type = EvaluationType.ENTITY,
		name = "isFrozen",
		description = "Tests if the target is fully frozen"
)
public class IsFrozenCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e.isFreezing();
	}

}
