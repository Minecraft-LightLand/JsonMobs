package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "dusk",
		description = "If the time is dusk, from 14000 to 18000 in-game time."
)
public class DuskCondition extends SkillCondition implements IPosCondition {

	@Override
	public boolean check(LevelPosYaw l) {
		long time = l.level().getDayTime();
		return time > 14000L && time < 18000L;
	}
}
