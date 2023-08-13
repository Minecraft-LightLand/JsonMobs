package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class SinglePosTargeter extends PosTargeter {

	public abstract Optional<LevelPosYaw> getBlock(TargeterContext init);

	@Override
	public Stream<LevelPosYaw> searchPos(TargeterContext init) {
		return getBlock(init).stream();
	}

}
