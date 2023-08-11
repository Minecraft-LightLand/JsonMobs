package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Singleton;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@ConditionType(
		value = EvaluationType.ENTITY,
		name = "hasAI",
		description = "Tests if target has AI"
)
@Singleton
public class HasAICondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e instanceof Mob mob && !mob.isNoAi();
	}

}
