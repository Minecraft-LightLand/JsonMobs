package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "ismonster",
		description = "If the target is a monster (spawn group = monster)"
)
public class IsMonsterCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return entity.getType().getCategory() == MobCategory.MONSTER;
	}

}
