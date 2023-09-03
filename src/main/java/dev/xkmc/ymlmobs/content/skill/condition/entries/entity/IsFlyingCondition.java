package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.player.Player;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = {"Ashijin", "lcy0x1"},
		name = "isflying",
		aliases = "flying",
		description = "If the target is flying. Doesn't count fall-flying, which is using gilding"
)
public class IsFlyingCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get() instanceof FlyingMob || e.get() instanceof Player player && player.getAbilities().flying;
	}

}
