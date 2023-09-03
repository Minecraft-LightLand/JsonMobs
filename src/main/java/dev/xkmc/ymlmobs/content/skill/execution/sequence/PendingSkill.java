package dev.xkmc.ymlmobs.content.skill.execution.sequence;

import dev.xkmc.ymlmobs.content.skill.core.MechanicInstance;

public record PendingSkill(MechanicInstance ins, ExecutionContext ctx) implements ExecutableEntry {

	@Override
	public ExecutionResult execute(ExecutionSequence seq) {
		return ins.run(seq, ctx.targeting().parent.trigger, ctx.targeting());
	}

}
