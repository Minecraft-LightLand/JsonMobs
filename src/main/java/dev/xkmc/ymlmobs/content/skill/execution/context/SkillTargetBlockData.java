package dev.xkmc.ymlmobs.content.skill.execution.context;

import dev.xkmc.ymlmobs.util.LevelPosYaw;

public class SkillTargetBlockData extends SkillModifiableData {

	private final SkillInitiateData initial;
	private final LevelPosYaw pos;

	public SkillTargetBlockData(SkillInitiateData initial, LevelPosYaw pos) {
		super(initial.getSkills(), initial.getPower());
		this.initial = initial;
		this.pos = pos;
	}

	public SkillInitiateData initial() {
		return initial;
	}

	public LevelPosYaw pos() {
		return pos;
	}

	public SkillCaster caster() {
		return initial().caster();
	}

}
