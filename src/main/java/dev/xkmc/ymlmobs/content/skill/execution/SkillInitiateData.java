package dev.xkmc.ymlmobs.content.skill.execution;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.init.YmlMobs;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

import javax.annotation.Nullable;
import java.util.List;

public class SkillInitiateData extends SkillModifiableData {

	public final TriggerContext trigger;

	private final LevelPosYaw origin;

	@Nullable
	private final SkillTargetingData parent;

	public SkillInitiateData(TriggerContext trigger, SkillMechanic base, @Nullable SkillTargetingData parent, LevelPosYaw origin) {
		super(List.of(base), 1);
		this.trigger = trigger;
		this.origin = origin;
		this.parent = parent;
	}

	public SkillCaster caster() {
		return trigger.getCaster();
	}

	@Nullable
	public EntityDataContext getTriggerEntity() {
		return trigger.getTriggerEntity();
	}

	public LevelPosYaw getOrigin() {
		return origin;
	}

	@Nullable
	public SkillTargetingData parent() {
		return parent;
	}

	public SkillTargetingData searchTarget(@Nullable SkillTargeter targeter) {
		if (targeter == null) {
			if (parent == null) {
				YmlMobs.LOGGER.error("No Targeter present in skill casted by " + trigger.getCaster().get());
				return new SkillTargetingData(this, TargetTypes.SINGLE_ENTITY, Either.right(List.of()));
			} else {
				return new SkillTargetingData(this, parent.type, parent.targets.mapBoth(
						l -> l.stream().map(SkillTargetEntityData::target).toList(),
						r -> r.stream().map(SkillTargetBlockData::pos).toList()
				));
			}
		} else {
			return new SkillTargetingData(this, targeter.getType(), targeter.search(this));
		}
	}
}
