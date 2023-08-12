package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.meta;

import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.MetaEntityTargeter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.stream.Stream;

@TargeterType(
		type = TargetTypes.META_ENTITY,
		author = "Ashijin",
		name = "livingInLine",
		aliases = {"entitiesInLine", "livingEntitiesInLine", "LEIL", "EIL"},
		description = "Targets random points in a cone shape"
)
public class LivingInLineTargeter extends MetaEntityTargeter {

	@Argument(name = "radius", aliases = "r", optional = true, description = "radius to search, default 1")
	protected double radius = 1;

	@Argument(name = "fromorigin", aliases = "fo", description = "starts from custom skill origin, default false", optional = true)
	protected boolean fromOrigin = false;

	@Override
	public Stream<Entity> getEntitiesFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return get(init, list.map(e -> e.target().pos()));
	}

	@Override
	public Stream<Entity> getEntitiesFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list) {
		return get(init, list.map(SkillTargetBlockData::pos));
	}

	public Stream<Entity> get(TargeterContext data, Stream<LevelPosYaw> list) {
		Vec3 sl;
		if (this.fromOrigin) {
			sl = data.getOrigin().asVec();
		} else {
			sl = data.caster().pos().asVec();
		}
		AABB aabb = new AABB(sl, sl);
		for (LevelPosYaw el : list.toList()) {
			aabb = aabb.minmax(new AABB(el.asVec(), el.asVec()));
		}
		return data.level().getEntities(
						EntityTypeTest.forClass(LivingEntity.class), aabb,
						e -> list.anyMatch(p -> pointToSegment(e.position(), sl, p.asVec()) <= radius))
				.stream().map(e -> e);
	}

	private static double pointToSegment(Vec3 p, Vec3 l1, Vec3 l2) {
		double line_dist = l1.distanceToSqr(l2);
		if (line_dist < 1e-3) return p.distanceTo(l1);
		double t = p.subtract(l1).dot(l2.subtract(l1)) / line_dist;
		t = Mth.clamp(t, 0, 1);
		return p.distanceTo(l1.scale(1 - t).add(l2.scale(t)));
	}
}
