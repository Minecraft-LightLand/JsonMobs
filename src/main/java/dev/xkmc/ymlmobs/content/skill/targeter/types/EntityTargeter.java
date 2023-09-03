package dev.xkmc.ymlmobs.content.skill.targeter.types;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.EntityTargetSorter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.utils.EntityFilter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public abstract class EntityTargeter extends SkillTargeter {

	@Argument(name = "limit", optional = true, description = "Limit of number of target entities. Default to 0, meaning no limit")
	protected int limit = 0;
	@Argument(name = "sortby", aliases = "sort", optional = true, description = "Sort option")
	protected EntityTargetSorter sorter = EntityTargetSorter.NONE;
	@Argument(name = "target", optional = true, description = "block type whitelist")
	protected List<EntityFilter> targetTypes = new ArrayList<>();
	@Argument(name = "ignore", optional = true, description = "block type blacklist")
	protected List<EntityFilter> ignoreTypes = new ArrayList<>();


	@Override
	public final Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init) {
		var ans = searchEntity(init).distinct().map(EntityDataContext::of).filter(e -> filter(init, e));
		return Either.left(ans.toList());
	}

	protected boolean filter(TargeterContext data, EntityDataContext entity) {
		return true;
	}

	public abstract Stream<Entity> searchEntity(TargeterContext init);

}
