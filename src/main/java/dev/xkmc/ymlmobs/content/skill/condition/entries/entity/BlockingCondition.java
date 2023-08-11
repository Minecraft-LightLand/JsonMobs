package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
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
	public boolean check(LivingEntity e) {
		return e.isBlocking();
	}

}
