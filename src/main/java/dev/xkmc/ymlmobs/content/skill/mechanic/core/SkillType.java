package dev.xkmc.ymlmobs.content.skill.mechanic.core;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.basic.*;

public enum SkillType {
	META(IMetaSkill.class),
	DELAY(DelaySkill.class),
	SELF(ISelfSkill.class),
	ENTITY(IEntitySkill.class),
	POS(IPosSkill.class);

	private final Class<? extends SkillMechanic> cls;

	SkillType(Class<? extends SkillMechanic> cls) {
		this.cls = cls;
	}

	public Class<?> getTypeClass() {
		return cls;
	}

}
