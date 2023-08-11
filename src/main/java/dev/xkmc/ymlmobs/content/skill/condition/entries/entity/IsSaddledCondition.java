package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Saddleable;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Seyarada",
		name = "isSaddled",
		aliases = {"saddled"},
		description = "If the target has a saddle"
)
public class IsSaddledCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return entity instanceof Saddleable sad && sad.isSaddled();
	}
}
