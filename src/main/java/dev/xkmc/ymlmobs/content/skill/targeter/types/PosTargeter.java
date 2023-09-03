package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.PosTargetSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.utils.BlockFilter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.core.DoubleType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class PosTargeter extends SkillTargeter {

	@Argument(name = "xoffset", aliases = {"xo", "x"}, optional = true, description = "x offset of the target positions")
	protected DataHolder<Double> xoffset = DoubleType.ZERO;
	@Argument(name = "yoffset", aliases = {"yo", "y"}, optional = true, description = "y offset of the target positions")
	protected DataHolder<Double> yoffset = DoubleType.ZERO;
	@Argument(name = "zoffset", aliases = {"zo", "z"}, optional = true, description = "z offset of the target positions")
	protected DataHolder<Double> zoffset = DoubleType.ZERO;
	@Argument(name = "forwardoffset", aliases = {"foffset", "fo"}, optional = true,
			description = "forward offset of target position. " +
					"Note that block targeters provide no directional information")
	protected DataHolder<Double> forwardOffset = DoubleType.ZERO;
	@Argument(name = "sideoffset", aliases = {"soffset", "so"}, optional = true,
			description = "side offset of target position. " +
					"Note that block targeters provide no directional information")
	protected DataHolder<Double> sideOffset = DoubleType.ZERO;

	@Nullable
	@Argument(name = "coordinatex", aliases = "cx", optional = true, description = "X coordinate override")
	protected DataHolder<Double> coordinateX = null;
	@Nullable
	@Argument(name = "coordinatey", aliases = "cy", optional = true, description = "Y coordinate override")
	protected DataHolder<Double> coordinateY = null;
	@Nullable
	@Argument(name = "coordinatez", aliases = "cz", optional = true, description = "Z coordinate override")
	protected DataHolder<Double> coordinateZ = null;
	@Nullable
	@Argument(name = "coordinateyaw", aliases = "cyaw", optional = true, description = "Yaw override")
	protected DataHolder<Double> coordinateYaw = null;
	@Nullable
	@Argument(name = "coordinatepitch", aliases = "cpitch", optional = true, description = "Pitch override")
	protected DataHolder<Double> coordinatePitch = null;
	@Argument(name = "blockCentered", aliases = "centered", optional = true, description = "Center X and Z coordinate to block center")
	protected boolean centered = false;
	@Argument(name = "limit", optional = true, description = "Limit of number of target positions. Default to 0, meaning no limit")
	protected int limit = 0;
	@Argument(name = "sortby", aliases = "sort", optional = true, description = "Sort option")
	protected PosTargetSorter sorter = PosTargetSorter.NONE;
	@Argument(name = "blocktypes", aliases = {"blocktype", "bt"}, optional = true, description = "block type whitelist")
	protected Set<BlockFilter> blockTypes = new HashSet<>();
	@Argument(name = "blockignores", aliases = {"blockignore", "bi"}, optional = true, description = "block type blacklist")
	protected Set<BlockFilter> blockIgnores = new HashSet<>();

	//TODO unused
	protected DataHolder<Double> length;
	protected DataHolder<Double> rotateX;
	protected DataHolder<Double> rotateY;
	protected DataHolder<Double> rotateZ;

	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		var ans = searchPos(init).distinct().map(e -> mutate(init, e));
		if (blockTypes.size() > 0) {
			ans = ans.filter(e -> blockTypes.stream().anyMatch(x -> x.test(e.getBlock())));
		}
		if (blockIgnores.size() > 0) {
			ans = ans.filter(e -> blockIgnores.stream().noneMatch(x -> x.test(e.getBlock())));
		}
		ans = switch (sorter) {
			case NONE -> ans;
			case NEAREST -> ans.sorted(Comparator.comparingDouble(e -> e.distanceSqr(init.getOrigin())));
			case FURTHEST -> ans.sorted(Comparator.comparingDouble(e -> -e.distanceSqr(init.getOrigin())));
			case RANDOM -> ans.sorted(Comparator.comparingInt(Record::hashCode));
		};
		if (limit > 0) {
			ans = ans.limit(limit);
		}
		return Either.right(ans.toList());
	}

	public LevelPosYaw mutate(TargeterContext init, LevelPosYaw pos) {
		var data = init.caster();

		pos = pos.offset(
				xoffset.get(data),
				yoffset.get(data),
				zoffset.get(data)
		);

		pos = pos.moveForward(new Vec3(this.forwardOffset.get(data), 0.0, this.sideOffset.get(data)));

		//location = location.add(location.getDirection().clone().multiply(len));
		//rotate

		double x = pos.x(), y = pos.y(), z = pos.z(), ry = pos.yaw(), rp = pos.pitch();
		if (coordinateX != null) x = coordinateX.get(data);
		if (coordinateY != null) y = coordinateY.get(data);
		if (coordinateZ != null) z = coordinateX.get(data);
		if (coordinateYaw != null) ry = coordinateYaw.get(data);
		if (coordinatePitch != null) rp = coordinatePitch.get(data);
		if (centered) {
			x = ((int) x) + 0.5;
			z = ((int) z) + 0.5;
		}
		pos = new LevelPosYaw(pos.level(), x, y, z, ry, rp);

		return pos;
	}


	public abstract Stream<LevelPosYaw> searchPos(TargeterContext init);


}
