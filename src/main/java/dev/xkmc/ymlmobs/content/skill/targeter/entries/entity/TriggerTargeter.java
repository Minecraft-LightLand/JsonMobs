package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;

import java.util.List;

@TargeterType(
		author = "Ashijin",
		name = "trigger",
		description = "Targets the entity that triggered the skill"
)
public class TriggerTargeter extends SkillTargeter {

	@Override
	public List<EntityDataContext> getEntities(SkillInitiateData data) {
		EntityDataContext entity = data.getTriggerEntity();
		return entity == null ? List.of() : List.of(entity);
	}
}
