package dev.xkmc.ymlmobs.content.skill.mechanic.core;

import dev.xkmc.ymlmobs.content.skill.core.SkillDefinition;
import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.DelaySkill;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.IEntitySkill;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.IPosSkill;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.ISelfSkill;

public enum SkillType {
	META(SkillDefinition.class),
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
