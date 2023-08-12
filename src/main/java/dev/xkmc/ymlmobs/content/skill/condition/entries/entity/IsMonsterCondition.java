package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.MobCategory;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "ismonster",
		description = "If the target is a monster (spawn group = monster)"
)
public class IsMonsterCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		return entity.get().getType().getCategory() == MobCategory.MONSTER;
	}

}
