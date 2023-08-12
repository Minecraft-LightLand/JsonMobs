package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.core.BlockPos;

import java.util.stream.Stream;

public abstract class BlockToBlockMetaTargeter extends MetaBlockTargeter {

	@Override
	public final Stream<BlockPos> getBlocksFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return getBlocks(init, list.map(e -> e.target().pos()));
	}

	@Override
	public final Stream<BlockPos> getBlocksFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list) {
		return getBlocks(init, list.map(SkillTargetBlockData::pos));
	}

	public abstract Stream<BlockPos> getBlocks(TargeterContext data, Stream<LevelPosYaw> list);

}
