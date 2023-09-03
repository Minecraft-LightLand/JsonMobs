package dev.xkmc.ymlmobs.content.skill.core;

import dev.xkmc.ymlmobs.content.skill.execution.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.execution.PendingSkill;

import java.util.LinkedList;
import java.util.List;

public class SkillDefinition extends SkillMechanic {

	protected List<MechanicInstance> skills = new LinkedList<>();
	protected boolean stopIfNoTargets = false;//TODO
	protected boolean inlineSkill = false;//TODO
	protected List<String> killMessages;//TODO

	//protected Optional<Skill> onCooldownSkill = Optional.empty(); TODO

	public SkillDefinition() {

	}

	@Override
	public ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		ExecutionSequence sub = seq.subSequence(ctx.forceSync());
		for (MechanicInstance ins : skills) {
			sub.schedule(new PendingSkill(ins, ctx));
		}
		return ExecutionResult.additional(sub);
	}
}
