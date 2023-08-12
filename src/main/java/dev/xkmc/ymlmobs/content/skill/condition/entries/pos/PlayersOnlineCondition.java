package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;

import java.util.Optional;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "playersOnline",
		aliases = {"onlinePlayerCount", "onlinePlayers"},
		description = "Matches how many players are online on the server"
)
public class PlayersOnlineCondition extends SkillCondition implements IPosCondition {

	@Argument(
			name = "amount",
			aliases = {"a"},
			description = "The number range to match",
			optional = true
	)
	public IRange amount = IRange.POSITIVE;

	@Override
	public boolean check(LevelPosYaw location) {
		return amount.test(Optional.ofNullable(location.level().getServer()).map(e -> e.getPlayerList().getPlayerCount()).orElse(0));
	}

}
