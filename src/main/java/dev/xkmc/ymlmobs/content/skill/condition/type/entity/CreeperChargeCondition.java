package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Singleton;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;

@ConditionType(
		author = "Joshinn",
		value = EvaluationType.ENTITY,
		name = "charged",
		aliases = {"isCharged", "creeperCharged"},
		description = "Whether or not the creeper is charged."
)
@Singleton
public class CreeperChargeCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e instanceof Creeper c && c.isPowered();
	}

}
