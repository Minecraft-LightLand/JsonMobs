package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.meta;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.MetaEntityTargeter;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.stream.Stream;

@TargeterType(
		type = TargetTypes.META_ENTITY,
		author = "Ashijin",
		name = "playersNearTargetLocations",
		aliases = {"playersNearTargetLocation", "PNTL"},
		description = "Targets all players near the inherited locations"
)
public class PlayersNearTargetLocationsTargeter extends MetaEntityTargeter {

	@Argument(name = "radius", aliases = "r", optional = true, description = "radius to search for, default 5")
	protected double radius = 5;

	@Override
	public Stream<Entity> getEntitiesFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return Stream.of();
	}

	@Override
	public Stream<Entity> getEntitiesFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list) {
		double r2 = radius * radius;
		var ans = Stream.<Entity>builder();
		for (Player pl : init.caster().get().level().players()) {
			if (list.anyMatch(pos -> pl.distanceToSqr(pos.pos().asVec()) < r2)) {
				ans.add(pl);
			}
		}
		return ans.build();
	}

}
