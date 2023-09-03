package dev.xkmc.ymlmobs.content.skill.mechanic.types;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillContext;

public abstract class IPosSkill extends SkillMechanic {

	@Override
	public final ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		var opt = ctx.targeting().targets.right();
		if (opt.isPresent()) {
			var list = opt.get();
			boolean succeed = false;
			for (var e : list) {
				succeed |= castAtPos(ctx, e).succeed();
			}
			if (succeed) return ExecutionResult.complete();
		}
		return ExecutionResult.noTarget();
	}

	public abstract ExecutionResult castAtPos(SkillContext ctx, SkillTargetBlockData entity);

}
