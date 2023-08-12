package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "playerNotWithin",
		aliases = {"playersnotwithin"},
		description = "Checks if any targetable players are within a radius of the target"
)
public class PlayerNotWithinCondition extends SkillCondition implements IPosCondition {

	@Argument(
			name = "distance",
			aliases = {"d"},
			description = "The radius to check in"
	)
	public double distance;

	@Override
	public boolean check(LevelPosYaw location) {
		for (var p : location.level().players()) {
			if (!p.isInvulnerable() && p.level() == location.level() && p.distanceToSqr(location.asVec()) < distance * distance) {
				return false;
			}
		}
		return true;
	}

}
