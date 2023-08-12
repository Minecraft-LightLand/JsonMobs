package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.init.YmlMobs;
import net.minecraft.core.BlockPos;

import java.util.stream.Stream;

public abstract class MetaBlockTargeter extends BlockTargeter {

	@Override
	public final Stream<BlockPos> searchBlock(TargeterContext init) {
		var parent = init.parent();
		if (parent == null) {
			YmlMobs.LOGGER.error("Meta targeter " + getClass().getSimpleName() + " are used without inherited targets, by " + init.real().caster().get());
			return Stream.of();
		}
		return parent.targets.map(l -> getBlocksFromEntities(init, l.stream()), r -> getBlocksFromBlocks(init, r.stream()));
	}

	public abstract Stream<BlockPos> getBlocksFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list);

	public abstract Stream<BlockPos> getBlocksFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list);

}
