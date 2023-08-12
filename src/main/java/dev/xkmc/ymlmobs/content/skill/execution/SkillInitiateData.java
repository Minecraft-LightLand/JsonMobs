package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;

import java.util.List;

public class SkillInitiateData extends SkillModifiableData {

	private final SkillCaster caster;

	public SkillInitiateData(SkillCaster caster, SkillMechanic base) {
		super(List.of(base), 1);
		this.caster = caster;
	}

	public SkillCaster caster() {
		return caster;
	}

}
