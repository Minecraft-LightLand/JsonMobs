package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "day",
		description = "If the time is day, from 2000 to 10000 in-game time"
)
public class DayCondition extends SkillCondition implements IPosCondition {

	@Override
	public boolean check(LevelPosYaw l) {
		long time = l.level().getDayTime();
		return time >= 2000L && time <= 10000L;
	}

}
