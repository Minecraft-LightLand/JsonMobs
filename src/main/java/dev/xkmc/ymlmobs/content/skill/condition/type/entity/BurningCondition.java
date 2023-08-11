package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Singleton;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		author = "Joshinn",
		value = EvaluationType.ENTITY,
		name = "burning",
		aliases = {"isburning", "isonfire"},
		description = "Whether or not the target entity is on fire."
)
@Singleton
public class BurningCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isOnFire();
	}

}
