package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Singleton;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "Ashijin",
		value = EvaluationType.ENTITY,
		name = "blocking",
		aliases = {"isblocking"},
		description = "Tests if the target entity is blocking with a shield"
)
@Singleton
public class BlockingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e.isBlocking();
	}

}
