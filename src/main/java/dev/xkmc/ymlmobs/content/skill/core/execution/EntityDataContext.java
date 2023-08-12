package dev.xkmc.ymlmobs.content.skill.core.execution;

import dev.xkmc.ymlparser.holder.DataContext;
import net.minecraft.world.entity.LivingEntity;

public abstract class EntityDataContext implements DataContext {

	public abstract LivingEntity get();

}
