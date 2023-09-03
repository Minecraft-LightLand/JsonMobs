package dev.xkmc.ymlmobs.content.skill.mechanic.core;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlparser.type.MetaTypeEntry;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class MechanicTypeRegistry extends MetaTypeRegistry<SkillMechanic> {

	public MechanicTypeRegistry(String name) {
		super(name);
	}

	@Override
	protected void checkValidity(MetaTypeEntry<SkillMechanic> entry) {
		super.checkValidity(entry);
		MechanicType type = entry.getClass().getAnnotation(MechanicType.class);
		if (type == null) {
			throw new IllegalStateException("Skill " + entry.id() + " has no type specified");
		}
		var et = type.type();
		if (!et.getTypeClass().isAssignableFrom(entry.val())) {
			throw new IllegalStateException("Skill " + entry.id() + " does not implement its type");
		}
	}

}
