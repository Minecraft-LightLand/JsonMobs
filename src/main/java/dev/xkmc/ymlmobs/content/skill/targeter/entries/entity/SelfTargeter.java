package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;

import java.util.Collection;
import java.util.List;

@TargeterType(
		author = "Ashijin",
		name = "self",
		aliases = {"caster", "boss", "mob"},
		description = "Targets the caster"
)
public class SelfTargeter extends SkillTargeter {

	@Override
	public Collection<EntityDataContext> getEntities(SkillInitiateData data) {
		return List.of(data.caster());
	}

}
