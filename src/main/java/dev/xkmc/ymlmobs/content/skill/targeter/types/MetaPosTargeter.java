package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.init.YmlMobs;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.stream.Stream;

public abstract class MetaPosTargeter extends PosTargeter {

	@Override
	public final Stream<LevelPosYaw> searchPos(TargeterContext init) {
		var parent = init.parent();
		if (parent == null) {
			YmlMobs.LOGGER.error("Meta targeter " + getClass().getSimpleName() + " are used without inherited targets, by " + init.real().caster().get());
			return Stream.of();
		}
		return parent.targets.map(l -> getPosFromEntities(init, l.stream()), r -> getPosFromPos(init, r.stream()));
	}

	public abstract Stream<LevelPosYaw> getPosFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list);

	public abstract Stream<LevelPosYaw> getPosFromPos(TargeterContext init, Stream<SkillTargetBlockData> list);

}
