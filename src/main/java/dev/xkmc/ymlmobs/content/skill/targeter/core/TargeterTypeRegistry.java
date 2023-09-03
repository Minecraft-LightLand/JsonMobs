package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlparser.type.MetaTypeEntry;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class TargeterTypeRegistry extends MetaTypeRegistry<SkillTargeter> {

	public TargeterTypeRegistry(String name) {
		super(name);
	}

	@Override
	protected void checkValidity(MetaTypeEntry<SkillTargeter> entry) {
		super.checkValidity(entry);
		TargeterType type = entry.getClass().getAnnotation(TargeterType.class);
		if (type == null) {
			throw new IllegalStateException("Targeter " + entry.id() + " has no type specified");
		}
		var et = type.type();
		if (!et.getTypeClass().isAssignableFrom(entry.val())) {
			throw new IllegalStateException("Targeter " + entry.id() + " does not implement its type");
		}
	}

}
