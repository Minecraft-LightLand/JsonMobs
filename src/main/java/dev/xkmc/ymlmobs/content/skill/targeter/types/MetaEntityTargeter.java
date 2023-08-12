package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.init.YmlMobs;
import net.minecraft.world.entity.Entity;

import java.util.stream.Stream;

public abstract class MetaEntityTargeter extends EntityTargeter {

	@Override
	public final Stream<Entity> searchEntity(TargeterContext init) {
		var parent = init.parent();
		if (parent == null) {
			YmlMobs.LOGGER.error("Meta targeter " + getClass().getSimpleName() + " are used without inherited targets, by " + init.real().caster().get());
			return Stream.of();
		}
		return parent.targets.map(l -> getEntitiesFromEntities(init, l.stream()), r -> getEntitiesFromBlocks(init, r.stream()));
	}

	public abstract Stream<Entity> getEntitiesFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list);

	public abstract Stream<Entity> getEntitiesFromBlocks(TargeterContext init, Stream<SkillTargetBlockData> list);

}
