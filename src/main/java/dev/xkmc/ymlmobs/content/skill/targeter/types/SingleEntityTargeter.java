package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class SingleEntityTargeter extends EntityTargeter {

	public abstract Optional<Entity> getEntity(TargeterContext init);

	@Override
	public Stream<Entity> searchEntity(TargeterContext init) {
		return getEntity(init).stream();
	}

}
