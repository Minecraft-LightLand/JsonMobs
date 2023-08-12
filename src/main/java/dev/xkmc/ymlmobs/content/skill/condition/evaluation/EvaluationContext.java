package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillCaster;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import javax.annotation.Nullable;

public record EvaluationContext(SkillCaster caster,
								@Nullable EntityDataContext focus,
								@Nullable LevelPosYaw pos
) {

	public static EvaluationContext ofCaster(SkillCaster caster) {
		return ofTarget(caster, caster);
	}

	public static EvaluationContext ofTarget(SkillCaster caster, @Nullable EntityDataContext target) {
		return new EvaluationContext(caster, target, target == null ? null : target.pos());
	}

	public static EvaluationContext ofTargetPos(SkillCaster caster, LevelPosYaw pos) {
		return new EvaluationContext(caster, null, pos);
	}

}
