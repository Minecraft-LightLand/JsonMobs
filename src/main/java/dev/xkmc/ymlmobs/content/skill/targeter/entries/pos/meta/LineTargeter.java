package dev.xkmc.ymlmobs.content.skill.targeter.entries.pos.meta;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.BlockToBlockMetaTargeter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@TargeterType(
		type = TargetTypes.META_BLOCK,
		author = "Ashijin",
		name = "line",
		description = "Targets points in a line from the caster to the target location"
)
public class LineTargeter extends BlockToBlockMetaTargeter {

	@Argument(name = "radius", aliases = "r", description = "spacing of the points around the line, default 1", optional = true)
	protected float radius = 1.0F;

	@Argument(name = "fromorigin", aliases = "fo", description = "starts from custom skill origin, default false", optional = true)
	protected boolean fromOrigin = false;

	@Override
	public Stream<BlockPos> getBlocks(TargeterContext data, Stream<LevelPosYaw> list) {
		LevelPosYaw sl = fromOrigin ? data.getOrigin() : data.caster().pos();
		Vec3 sv = sl.asVec();
		return list.flatMap(lx -> {
			int c = (int) Math.ceil(sv.distanceTo(lx.asVec()) / (double) this.radius) - 1;
			if (c > 1) {
				Vec3 v = lx.asVec().subtract(sv).normalize().scale(this.radius);
				return IntStream.range(1, c).mapToObj(i -> BlockPos.containing(sv.add(v.scale(i))));
			}
			return Stream.of();
		});
	}

}
