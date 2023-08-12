package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillCaster;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public record EvaluationContext(SkillCaster caster,
								@Nullable EntityDataContext focus,
								@Nullable LevelPosYaw pos
) {

	public static EvaluationContext ofCaster(SkillCaster caster) {
		return ofTarget(caster, caster);
	}

	public static EvaluationContext ofTarget(SkillCaster caster, @Nullable EntityDataContext target) {
		return new EvaluationContext(caster, target, target == null ? null : LevelPosYaw.of(target.get()));
	}

	public static EvaluationContext ofBlockPos(SkillCaster caster, BlockPos pos) {
		return new EvaluationContext(caster, caster, LevelPosYaw.of(caster.get().level(), pos));
	}

}
