package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "dawn",
		description = "If the time is dawn, from 22000 to 2000 in-game time"
)
public class DawnCondition extends SkillCondition implements IPosCondition {

	@Override
	public boolean check(LevelPosYaw l) {
		long time = l.level().getDayTime();
		return time > 22000L || time < 2000L;
	}

}
