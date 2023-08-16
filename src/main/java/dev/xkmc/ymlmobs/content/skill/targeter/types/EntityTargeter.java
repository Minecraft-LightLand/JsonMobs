package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.EntityTargetSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single.SelfTargeter;
import dev.xkmc.ymlmobs.content.skill.utils.EntityFilter;
import dev.xkmc.ymlmobs.init.data.YMConfig;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public abstract class EntityTargeter extends SkillTargeter {

	@Argument(name = "targetself", optional = true,
			description = "targets caster itself. " +
					"Default on for SelfTargeter, " +
					"and follow global config settings (default off) otherwise.")
	protected boolean targetSelf = (this instanceof SelfTargeter) || YMConfig.COMMON.isTargetSelf.get();


	@Argument(name = "limit", optional = true, description = "Limit of number of target entities. Default to 0, meaning no limit")
	protected int limit = 0;
	@Argument(name = "sortby", aliases = "sort", optional = true, description = "Sort option")
	protected EntityTargetSorter sorter = EntityTargetSorter.NONE;
	@Argument(name = "target", aliases = {"blocktype", "bt"}, optional = true, description = "block type whitelist")
	protected List<EntityFilter> targetTypes = new ArrayList<>();
	@Argument(name = "ignore", aliases = {"blockignore", "bi"}, optional = true, description = "block type blacklist")
	protected List<EntityFilter> ignoreTypes = new ArrayList<>();


	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		return Either.left(searchEntity(init).distinct().map(EntityDataContext::of).toList());
	}

	protected boolean filter(TargeterContext data, EntityDataContext entity) {

	}

	public abstract Stream<Entity> searchEntity(TargeterContext init);

}
