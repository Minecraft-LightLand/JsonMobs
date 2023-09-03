package dev.xkmc.ymlmobs.content.skill.mechanic.types.basic;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillContext;

public abstract class IEntitySkill extends SkillMechanic {

	@Override
	public final ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		var opt = ctx.targeting().targets.left();
		if (opt.isPresent()) {
			var list = opt.get();
			boolean succeed = false;
			for (var e : list) {
				succeed |= castAtEntity(ctx, e).succeed();
			}
			if (succeed) return ExecutionResult.complete();
		}
		return ExecutionResult.noTarget();
	}

	public abstract ExecutionResult castAtEntity(SkillContext ctx, SkillTargetEntityData entity);

}
