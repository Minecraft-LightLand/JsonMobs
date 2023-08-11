package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@ConditionType(
		value = EvaluationType.ENTITY,
		author = {"Ashijin", "lcy0x1"},
		name = "isflying",
		aliases = "flying",
		description = "If the target is flying"
)
public class IsFlyingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e instanceof FlyingMob || e.isFallFlying() || e instanceof Player player && player.getAbilities().flying;
	}

}
