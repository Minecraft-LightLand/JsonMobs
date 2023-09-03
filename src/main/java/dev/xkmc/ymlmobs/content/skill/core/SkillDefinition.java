package dev.xkmc.ymlmobs.content.skill.core;

import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.PendingSkill;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.MechanicType;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillType;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.basic.IMetaSkill;

import java.util.LinkedList;
import java.util.List;

@MechanicType(
		type = SkillType.META,
		name = "metaskill",
		description = "meta skill that holds several mechanics"
)
public class SkillDefinition extends IMetaSkill {

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
