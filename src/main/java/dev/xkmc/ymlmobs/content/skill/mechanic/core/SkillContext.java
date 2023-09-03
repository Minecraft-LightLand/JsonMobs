package dev.xkmc.ymlmobs.content.skill.mechanic.core;

import dev.xkmc.ymlmobs.content.skill.execution.context.TriggerContext;
import net.minecraft.util.RandomSource;

public interface SkillContext {

	TriggerContext getTrigger();

	RandomSource getRandom();
}
