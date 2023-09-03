package dev.xkmc.ymlmobs.content.skill.mechanic.types;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.MechanicType;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillType;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.holder.DataHolder;

@MechanicType(
		type = SkillType.DELAY,
		name = "delay",
		description = "delay execution of later mechanics"
)
public class DelaySkill extends SkillMechanic {

	@Argument(name = "delay", aliases = "t", description = "delay execution for x ticks")
	public DataHolder<Integer> delay;

	@Override
	public ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		seq.delay(delay.get(ctx.targeting().parent.caster()));
		return ExecutionResult.complete();
	}

}
