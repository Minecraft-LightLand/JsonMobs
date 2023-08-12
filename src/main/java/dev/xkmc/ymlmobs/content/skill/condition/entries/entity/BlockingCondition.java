package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "Ashijin",
		type = EvaluationType.ENTITY,
		name = "blocking",
		aliases = {"isblocking"},
		description = "Tests if the target entity is blocking with a shield"
)
public class BlockingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get() instanceof LivingEntity le && le.isBlocking();
	}

}
