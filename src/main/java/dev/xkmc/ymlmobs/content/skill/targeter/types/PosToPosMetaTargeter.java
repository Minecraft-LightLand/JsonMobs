package dev.xkmc.ymlmobs.content.skill.targeter.types;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetBlockData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetEntityData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.stream.Stream;

public abstract class PosToPosMetaTargeter extends MetaPosTargeter {

	@Override
	public final Stream<LevelPosYaw> getPosFromEntities(TargeterContext init, Stream<SkillTargetEntityData> list) {
		return getPos(init, list.map(e -> e.target().pos()));
	}

	@Override
	public final Stream<LevelPosYaw> getPosFromPos(TargeterContext init, Stream<SkillTargetBlockData> list) {
		return getPos(init, list.map(SkillTargetBlockData::pos));
	}

	public abstract Stream<LevelPosYaw> getPos(TargeterContext data, Stream<LevelPosYaw> list);

}
