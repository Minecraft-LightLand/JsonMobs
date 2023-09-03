package dev.xkmc.ymlmobs.content.skill.execution.sequence;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetingData;
import dev.xkmc.ymlmobs.content.skill.execution.context.TriggerContext;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillContext;

public record ExecutionContext(SkillTargetingData targeting, boolean targetIsOrigin, boolean forceSync)
		implements SkillContext {

	@Override
	public TriggerContext getTrigger() {
		return targeting.parent.trigger;
	}

}
