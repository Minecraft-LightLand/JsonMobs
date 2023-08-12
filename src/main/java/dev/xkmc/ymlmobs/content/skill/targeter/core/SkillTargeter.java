package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetingData;

import java.util.List;

public class SkillTargeter {
	public static final SkillTargeter TRIGGER = new SkillTargeter();

	public SkillTargetingData collectTargets(SkillInitiateData init) {
		return new SkillTargetingData(init, List.of(), List.of());//TODO
	}
}
