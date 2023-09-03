package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetingData;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public record TargeterContext(SkillInitiateData real, EntityDataContext caster) {

	@Nullable
	public SkillTargetingData parent() {
		return real.parent();
	}

	public LevelPosYaw getOrigin() {
		return real().getOrigin();
	}

	public Level level() {
		return caster().get().level();
	}
}
