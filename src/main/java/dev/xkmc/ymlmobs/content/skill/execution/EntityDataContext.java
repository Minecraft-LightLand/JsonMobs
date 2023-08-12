package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.holder.DataContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

public class EntityDataContext implements DataContext {

	public static EntityDataContext of(Entity entity) {
		return new EntityDataContext(entity);
	}

	private final Entity entity;

	public EntityDataContext(Entity entity) {
		this.entity = entity;
	}

	public Entity get() {
		return entity;
	}

	@Override
	public RandomSource getRandom() {
		return entity.level().getRandom();
	}

	public LevelPosYaw pos() {
		return LevelPosYaw.of(entity);
	}

}
