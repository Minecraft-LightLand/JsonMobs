package dev.xkmc.ymlmobs.content.skill.execution;

public class SkillTargetEntityData extends SkillModifiableData {

	private final SkillInitiateData initial;
	private final EntityDataContext target;

	public SkillTargetEntityData(SkillInitiateData initial, EntityDataContext target) {
		super(initial.getSkills(), initial.getPower());
		this.initial = initial;
		this.target = target;
	}

	public SkillInitiateData initial() {
		return initial;
	}

	public EntityDataContext pos() {
		return target;
	}

}
