package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import net.minecraft.core.BlockPos;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class SingleBlockTargeter extends BlockTargeter {

	public abstract Optional<BlockPos> getBlock(TargeterContext init);

	@Override
	public Stream<BlockPos> searchBlock(TargeterContext init) {
		return getBlock(init).stream();
	}

}
