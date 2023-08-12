package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.FilterSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.holder.DataHolder;
import net.minecraft.core.BlockPos;

import java.util.Collection;
import java.util.stream.Stream;

public abstract class BlockTargeter extends SkillTargeter {

	protected DataHolder<Double> xoffset;
	protected DataHolder<Double> yoffset;
	protected DataHolder<Double> zoffset;
	protected DataHolder<Double> forwardOffset;
	protected DataHolder<Double> sideOffset;
	protected DataHolder<Double> rotateX;
	protected DataHolder<Double> rotateY;
	protected DataHolder<Double> rotateZ;
	protected DataHolder<Double> length;
	protected DataHolder<Double> coordinateX;
	protected DataHolder<Double> coordinateY;
	protected DataHolder<Double> coordinateZ;
	protected DataHolder<Double> coordinateYaw;
	protected DataHolder<Double> coordinatePitch;
	protected DataHolder<String> blockTypes;
	protected DataHolder<String> blockIgnores;
	protected boolean statics = false;
	protected boolean offsets = false;
	protected boolean advOffset = false;
	protected boolean rotated = false;
	protected boolean centered = false;
	private final int limit = 0;
	private final FilterSorter sorter = FilterSorter.NONE;

	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		return Either.right(searchBlock(init).distinct().map(e -> LevelPosYaw.of(init.level(), e)).toList());
	}

	public abstract Stream<BlockPos> searchBlock(TargeterContext init);

}
