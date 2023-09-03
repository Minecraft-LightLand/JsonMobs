package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.content.skill.targeter.types.*;

public enum TargetTypes {
	SINGLE_BLOCK(SinglePosTargeter.class, true, false, true),
	MULTI_BLOCK(MultiPosTargeter.class, false, false, true),
	META_BLOCK(MetaPosTargeter.class, false, true, true),
	SINGLE_ENTITY(SingleEntityTargeter.class, true, false, false),
	MULTI_ENTITY(MultiEntityTargeter.class, false, false, false),
	META_ENTITY(MetaEntityTargeter.class, false, true, false);

	private final Class<? extends SkillTargeter> cls;
	private final boolean single, meta, block;

	TargetTypes(Class<? extends SkillTargeter> cls, boolean single, boolean meta, boolean block) {
		this.cls = cls;
		this.single = single;
		this.meta = meta;
		this.block = block;
	}

	public Class<?> getTypeClass() {
		return cls;
	}

	public boolean isSingle() {
		return single;
	}

	public boolean isMeta() {
		return meta;
	}

	public boolean isBlock() {
		return block;
	}

	public boolean isEntity() {
		return !block;
	}

}
