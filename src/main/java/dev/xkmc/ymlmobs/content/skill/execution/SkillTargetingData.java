package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.List;

public class SkillTargetingData {

	private final SkillInitiateData parent;
	public final List<SkillTargetEntityData> entityTargets;
	public final List<SkillTargetBlockData> blockTargets;

	public SkillTargetingData(SkillInitiateData parent, List<EntityDataContext> entityTargets, List<LevelPosYaw> blockTargets) {
		this.parent = parent;
		this.entityTargets = entityTargets.stream().map(e -> new SkillTargetEntityData(parent, e)).toList();
		this.blockTargets = blockTargets.stream().map(e -> new SkillTargetBlockData(parent, e)).toList();
	}

}
