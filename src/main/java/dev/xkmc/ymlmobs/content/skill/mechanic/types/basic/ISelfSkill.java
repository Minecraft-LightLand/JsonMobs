package dev.xkmc.ymlmobs.content.skill.mechanic.types.basic;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillContext;

public abstract class ISelfSkill extends SkillMechanic {

	@Override
	public final ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		return cast(ctx);
	}

	public abstract ExecutionResult cast(SkillContext ctx);

}
