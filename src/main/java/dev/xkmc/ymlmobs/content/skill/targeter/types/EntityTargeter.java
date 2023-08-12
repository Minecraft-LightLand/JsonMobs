package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.FilterSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single.SelfTargeter;
import dev.xkmc.ymlmobs.content.skill.utils.EntityFilter;
import dev.xkmc.ymlmobs.init.data.YMConfig;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public abstract class EntityTargeter extends SkillTargeter {

	private final boolean targetSelf = (this instanceof SelfTargeter) || YMConfig.COMMON.isTargetSelf.get();
	private final boolean targetPlayers = YMConfig.COMMON.isTargetPlayers.get();
	private final boolean targetArmorStands = YMConfig.COMMON.isTargetArmorStands.get();
	private final boolean targetMarkers = YMConfig.COMMON.isTargetMarkers.get();
	private final boolean targetCreativeMode = YMConfig.COMMON.isTargetCreativeMode.get();
	private final boolean targetSpectatorMode = YMConfig.COMMON.isTargetSpectatorMode.get();
	private final boolean targetCitizensNPCs = YMConfig.COMMON.isTargetCitizensNPCs.get();
	private final boolean targetAnimals = YMConfig.COMMON.isTargetAnimals.get();
	private final boolean targetCreatures = YMConfig.COMMON.isTargetCreatures.get();
	private final boolean targetMonsters = YMConfig.COMMON.isTargetMonsters.get();
	private final boolean targetGroundMobs = true;
	private final boolean targetWaterMobs = YMConfig.COMMON.isTargetWaterMobs.get();
	private final boolean targetFlyingMobs = YMConfig.COMMON.isTargetFlyingMobs.get();
	private final boolean targetSameFaction = YMConfig.COMMON.isTargetSameFaction.get();
	private final boolean targetOwner = YMConfig.COMMON.isTargetOwner.get();
	private final boolean targetNonMythic = YMConfig.COMMON.isTargetNonMythic.get();
	private final boolean targetVillagers = YMConfig.COMMON.isTargetVillagers.get();
	private final int limit = 0;
	private final FilterSorter sorter = FilterSorter.NONE;
	private final List<EntityFilter> ignoreTypes = new ArrayList<>();


	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		return Either.left(searchEntity(init).distinct().map(EntityDataContext::of).toList());
	}

	public abstract Stream<Entity> searchEntity(TargeterContext init);

}
