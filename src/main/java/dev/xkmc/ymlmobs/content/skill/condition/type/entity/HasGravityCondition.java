package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Singleton;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "jaylawl",
		value = EvaluationType.ENTITY,
		name = "hasgravity",
		aliases = {"gravity"},
		description = "Tests if the target has gravity"
)
@Singleton
public class HasGravityCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity target) {
		return !target.isNoGravity();
	}

}
