package dev.xkmc.ymlmobs.content.skill.execution.context;

import javax.annotation.Nullable;

public interface TriggerContext {

	@Nullable
	EntityDataContext getTriggerEntity();

	SkillCaster getCaster();
}
