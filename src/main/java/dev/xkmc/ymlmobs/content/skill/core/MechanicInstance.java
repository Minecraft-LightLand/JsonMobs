package dev.xkmc.ymlmobs.content.skill.core;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetingData;
import dev.xkmc.ymlmobs.content.skill.execution.context.TriggerContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MechanicInstance {

	public static class Builder {

		private final SkillMechanic type;
		@Nullable
		private SkillTargeter target = null;
		private SkillTrigger trigger = SkillTrigger.COMBAT;
		private HealthModifier healthMod = HealthModifier.NULL;
		private double chance = 1;

		private final List<ConditionInstance> cond = new ArrayList<>();
		private final List<ConditionInstance> casterCond = new ArrayList<>();
		private final List<ConditionInstance> triggerCond = new ArrayList<>();

		Builder(SkillMechanic type) {
			this.type = type;
		}

		public MechanicInstance build() {
			return new MechanicInstance(type, target, trigger, cond, casterCond, triggerCond, healthMod, chance);
		}

		public void setTarget(SkillTargeter target) {
			this.target = target;
		}

		public void setTrigger(SkillTrigger trigger) {
			this.trigger = trigger;
		}

		public void addTriggerCond(ConditionInstance cond) {
			triggerCond.add(cond);
		}

		public void addCasterCond(ConditionInstance cond) {
			casterCond.add(cond);
		}

		public void setHealthMod(HealthModifier mod) {
			this.healthMod = mod;
		}

		public void setChance(Double chance) {
			this.chance = chance;
		}

	}

	public static MechanicInstance of(SkillMechanic type) {
		return builder(type).build();
	}

	public static MechanicInstance.Builder builder(SkillMechanic type) {
		return new MechanicInstance.Builder(type);
	}

	protected final SkillMechanic mechanic;
	@Nullable
	protected final SkillTargeter targeter;
	protected final SkillTrigger trigger;
	protected final HealthModifier healthMod;
	protected final double chance;
	protected final List<ConditionInstance> conditions;
	protected final List<ConditionInstance> conditionsTarget;
	protected final List<ConditionInstance> conditionsTrigger;

	@Argument(name = "forceSync", aliases = {"sync"}, optional = true, description = "Sets the execution of the mechanic to the main thread instead of async.")
	protected boolean forceSync = false;

	@Argument(name = "targetIsOrigin", optional = true, description = "Sets the origin to the target's location instead of the caster's. Works on Mechanics only.")
	protected boolean targetIsOrigin = false;

	@Nullable
	@Argument(name = "origin", optional = true, description = "Sets the origin of the skill")
	protected SkillTargeter originOverride = null;

	//protected final Map<UUID, Long> cooldowns = Maps.newConcurrentMap();
	//protected boolean executeAfterDeath = false;

	private MechanicInstance(SkillMechanic type, @Nullable SkillTargeter target, SkillTrigger trigger,
							 List<ConditionInstance> conditions,
							 List<ConditionInstance> conditionsTarget,
							 List<ConditionInstance> conditionsTrigger,
							 HealthModifier healthMod, double chance) {
		this.mechanic = type;
		this.targeter = target;
		this.trigger = trigger;
		this.conditions = conditions;
		this.conditionsTarget = conditionsTarget;
		this.conditionsTrigger = conditionsTrigger;
		this.healthMod = healthMod;
		this.chance = chance;
	}

	public ExecutionResult run(ExecutionSequence seq, TriggerContext trigger, @Nullable SkillTargetingData parent) {
		LevelPosYaw origin;
		if (parent == null) {
			origin = trigger.getCaster().pos();
		} else {
			origin = parent.parent.getOrigin();
		}
		SkillInitiateData init = new SkillInitiateData(trigger, mechanic, parent, origin);
		for (var c : conditionsTrigger) {
			c.processTrigger(init, trigger);
			if (init.isRemoved()) break;
		}
		if (!init.isRemoved()) {
			for (var c : conditions) {
				c.processCaster(init);
				if (init.isRemoved()) break;
			}
		}
		init.lock();
		if (init.size() == 0) return ExecutionResult.noTarget();
		SkillTargetingData targeting = init.searchTarget(targeter);
		List<ConditionInstance> targetCond = new ArrayList<>();
		if (targeter != null) {
			targetCond.addAll(targeter.targetConditions);
		}
		targetCond.addAll(conditionsTarget);
		targeting.filterTarget(targetCond);
		ExecutionContext ctx = new ExecutionContext(targeting, targetIsOrigin, forceSync);
		return mechanic.run(seq, ctx);
	}

}
