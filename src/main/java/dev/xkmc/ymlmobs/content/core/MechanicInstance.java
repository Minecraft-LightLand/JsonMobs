package dev.xkmc.ymlmobs.content.core;

import com.google.common.collect.Maps;
import dev.xkmc.ymlparser.argument.Argument;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MechanicInstance {

	public static MechanicInstance of(SkillMechanic type) {
		return of(type, null, SkillTrigger.COMBAT, HealthModifier.NULL, 1);
	}

	public static MechanicInstance of(SkillMechanic type, @Nullable SkillTargeter target, SkillTrigger trigger, HealthModifier healthMod, double chance) {
		return new MechanicInstance(type, target, trigger, healthMod, chance);
	}

	@Nullable
	protected final SkillTargeter targeter;
	protected final SkillTrigger trigger;
	protected final HealthModifier healthMod;
	protected final double chance;


	protected boolean executeAfterDeath = false;

	@Argument(name = "forceSync", aliases = {"sync"}, optional = true, description = "Sets the execution of the mechanic to the main thread instead of async.")
	protected boolean forceSync = false;

	@Argument(name = "targetIsOrigin", optional = true, description = "Sets the origin to the target's location instead of the caster's")
	protected boolean targetIsOrigin = false;

	@Nullable
	@Argument(name = "origin", optional = true, description = "Sets the origin of the skill")
	protected SkillTargeter originOverride = null;

	protected final Map<UUID, Long> cooldowns = Maps.newConcurrentMap();
	protected final List<SkillCondition> conditions = new ArrayList<>();
	protected final List<SkillCondition> conditionsTarget = new ArrayList<>();
	protected final List<SkillCondition> conditionsTrigger = new ArrayList<>();

	private MechanicInstance(SkillMechanic type, @Nullable SkillTargeter target, SkillTrigger trigger, HealthModifier healthMod, double chance) {
		this.healthMod = healthMod;
		this.chance = chance;
		this.targeter = target;
		this.trigger = trigger;
	}

}
