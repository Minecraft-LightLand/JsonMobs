package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.meta;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.MetaEntityTargeter;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.stream.Stream;

@TargeterType(
		type = TargetTypes.META_ENTITY,
		author = "Ashijin",
		name = "livingNearTargetLocation",
		aliases = {"LNTL", "ENTL", "ENT"},
		description = "Targets entities near the target location"
)
public class LivingNearTargetLocationTargeter extends MetaEntityTargeter {

	@Argument(name = "radius", aliases = "r", optional = true, description = "radius to search for, default 5")
	public double radius;

	@Override
	public Stream<Entity> getEntitiesFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return Stream.of();
	}

	@Override
	public Stream<Entity> getEntitiesFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list) {
		AABB aabb = null;
		for (var el : list.toList()) {
			Vec3 v = el.pos().asVec();
			AABB ab = new AABB(v, v).inflate(radius);
			aabb = aabb == null ? ab : aabb.minmax(ab);
		}
		if (aabb == null) {
			return Stream.of();
		}
		double r2 = radius * radius;
		return init.level().getEntities(
						EntityTypeTest.forClass(LivingEntity.class), aabb,
						e -> list.anyMatch(p -> e.distanceToSqr(p.pos().asVec()) <= r2))
				.stream().map(e -> e);
	}

}
