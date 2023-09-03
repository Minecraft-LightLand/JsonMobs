package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.meta;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.MetaEntityTargeter;
import net.minecraft.world.entity.Entity;

import java.util.stream.Stream;

@TargeterType(
		type = TargetTypes.META_ENTITY,
		author = "Ashijin",
		name = "targetedTarget",
		aliases = {"targeted"},
		description = "Targets the inherited targets"
)
public class TargetedTargetTargeter extends MetaEntityTargeter {

	@Override
	public Stream<Entity> getEntitiesFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return list.map(e -> e.target().get());
	}

	@Override
	public Stream<Entity> getEntitiesFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list) {
		return Stream.of();
	}
}
