package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.Collection;
import java.util.List;

public class SkillTargeter {
	public static final SkillTargeter TRIGGER = new SkillTargeter();

	public Collection<EntityDataContext> getEntities(SkillInitiateData init) {
		return List.of();
	}

	public Collection<LevelPosYaw> getBlocks(SkillInitiateData init) {
		return List.of();
	}

}
