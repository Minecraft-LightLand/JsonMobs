package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ashijin",
		name = "isplayer",
		description = "If the target is a real player"
)
public class IsPlayerCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext entity) {
		return entity.get() instanceof Player player && !(player instanceof FakePlayer);
	}
}
