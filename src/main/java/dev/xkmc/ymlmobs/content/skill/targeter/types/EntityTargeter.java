package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.EntityTargetSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.PosTargetSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single.SelfTargeter;
import dev.xkmc.ymlmobs.content.skill.utils.BlockFilter;
import dev.xkmc.ymlmobs.content.skill.utils.EntityFilter;
import dev.xkmc.ymlmobs.init.data.YMConfig;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Entity;

import java.util.*;
import java.util.stream.Stream;

public abstract class EntityTargeter extends SkillTargeter {

	@Argument(name = "targetself", optional = true,
			description = "targets caster itself. " +
					"Default on for SelfTargeter, " +
					"and follow global config settings (default off) otherwise.")
	protected boolean targetSelf = (this instanceof SelfTargeter) || YMConfig.COMMON.isTargetSelf.get();

	@Argument(name = "targetplayers", optional = true, description = "targets player. Default on (configurable)")
	protected boolean targetPlayers = YMConfig.COMMON.isTargetPlayers.get();
	@Argument(name = "targetcreative", optional = true, description = "targets invulnerable. Default on (configurable)")
	protected boolean targetCreativeMode = YMConfig.COMMON.isTargetCreativeMode.get();
	@Argument(name = "targetanimals", optional = true, description = "targets animals. Default on (configurable)")
	protected boolean targetAnimals = YMConfig.COMMON.isTargetAnimals.get();
	@Argument(name = "targetcreatures", optional = true, description = "targets creatures. Default on (configurable)")
	protected boolean targetCreatures = YMConfig.COMMON.isTargetCreatures.get();
	@Argument(name = "targetmonsters", optional = true, description = "targets monsters. Default on (configurable)")
	protected boolean targetMonsters = YMConfig.COMMON.isTargetMonsters.get();
	@Argument(name = "targetwatermobs", optional = true, description = "targets water mobs. Default on (configurable)")
	protected boolean targetWaterMobs = YMConfig.COMMON.isTargetWaterMobs.get();
	@Argument(name = "targetflyingmobs", optional = true, description = "targets flying mobs. Default on (configurable)")
	protected boolean targetFlyingMobs = YMConfig.COMMON.isTargetFlyingMobs.get();
	@Argument(name = "targetsamefaction", optional = true, description = "targets mobs of same faction. Default on (configurable)")
	protected boolean targetSameFaction = YMConfig.COMMON.isTargetSameFaction.get();
	@Argument(name = "targetowner", optional = true, description = "targets owner. Default on (configurable)")
	protected boolean targetOwner = YMConfig.COMMON.isTargetOwner.get();

	@Argument(name = "limit", optional = true, description = "Limit of number of target entities. Default to 0, meaning no limit")
	protected int limit = 0;
	@Argument(name = "sortby", aliases = "sort", optional = true, description = "Sort option")
	protected EntityTargetSorter sorter = EntityTargetSorter.NONE;
	@Argument(name = "target", aliases = {"blocktype", "bt"}, optional = true, description = "block type whitelist")
	protected Set<BlockFilter> blockTypes = new HashSet<>();
	@Argument(name = "ignore", aliases = {"blockignore", "bi"}, optional = true, description = "block type blacklist")
	protected Set<BlockFilter> blockIgnores = new HashSet<>();

	protected List<EntityFilter> targetTypes = new ArrayList<>();
	protected List<EntityFilter> ignoreTypes = new ArrayList<>();

	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		return Either.left(searchEntity(init).distinct().map(EntityDataContext::of).toList());
	}

	protected boolean filter(TargeterContext data, EntityDataContext entity) {

	}

	public abstract Stream<Entity> searchEntity(TargeterContext init);

}
