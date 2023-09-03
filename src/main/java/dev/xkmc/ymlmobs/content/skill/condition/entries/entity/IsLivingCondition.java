package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import net.minecraft.world.entity.decoration.ArmorStand;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "isliving",
		description = "If the target is living"
)
public class IsLivingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		return !(entity.get() instanceof ArmorStand);
	}
}
