package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "playersInWorld",
		description = "Matches how many players are in the target level"
)
public class PlayersInWorldCondition extends SkillCondition implements IPosCondition {

	@Argument(
			name = "amount",
			aliases = {"a"},
			description = "The number range to match",
			optional = true
	)
	public IRange amount = IRange.POSITIVE;

	@Override
	public boolean check(LevelPosYaw location) {
		return this.amount.test(location.level().players().size());
	}

}
