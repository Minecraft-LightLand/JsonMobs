package dev.xkmc.ymlmobs.content.skill.execution;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.Collection;
import java.util.List;

public class SkillTargetingData {

	public final SkillInitiateData parent;
	public final TargetTypes type;
	public final Either<List<SkillTargetEntityData>, List<SkillTargetBlockData>> targets;

	public SkillTargetingData(SkillInitiateData parent, TargetTypes type, Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> targets) {
		this.parent = parent;
		this.type = type;
		this.targets = targets.mapBoth(
				l -> l.stream().map(e -> new SkillTargetEntityData(parent, e)).toList(),
				r -> r.stream().map(e -> new SkillTargetBlockData(parent, e)).toList()
		);
	}

	public void filterTarget(List<ConditionInstance> conditionsTarget) {
		targets.left().ifPresent(e -> e.removeIf(data -> {
			for (var c : conditionsTarget) {
				c.processTargetEntity(this, data);
				if (data.isRemoved()) break;
			}
			data.lock();
			return data.size() == 0;
		}));
		targets.right().ifPresent(e -> e.removeIf(data -> {
			for (var c : conditionsTarget) {
				c.processTargetBlock(this, data);
				if (data.isRemoved()) break;
			}
			data.lock();
			return data.size() == 0;
		}));
	}

}
