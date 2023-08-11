package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "isInSurvivalMode",
		aliases = {"inSurvivalMode"},
		description = "If the target is in survival mode, or in other words, can be damaged"
)
public class InSurvivalModeCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return !entity.isInvulnerable();
	}

}
