package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "isCreeperPrimed",
		description = "If the target creeper is primed to explode"
)
public class IsCreeperPrimedCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return entity instanceof Creeper c && c.isIgnited();
	}

}
