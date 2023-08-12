package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.action.RequiredAction;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;
import dev.xkmc.ymlmobs.content.skill.execution.*;
import dev.xkmc.ymlparser.argument.EntryBuilder;

public record ConditionInstance(ISkillCondition condition, ConditionAction action) {

	public static ConditionInstance of(ISkillCondition cond, ConditionAction action) {
		return new ConditionInstance(cond, action);
	}

	public static ConditionInstance of(ISkillCondition cond, boolean action) {
		return new ConditionInstance(cond, action ? EntryBuilder.create(RequiredAction.class) : EntryBuilder.create(ConditionAction.class));
	}

	public void processCaster(SkillInitiateData data) {
		EvaluationContext ctx = EvaluationContext.ofCaster(data.caster());
		action.process(ctx, data, condition().evaluate(ctx));
	}

	public void processTrigger(SkillInitiateData data, TriggerContext trigger) {
		EvaluationContext ctx = EvaluationContext.ofTarget(data.caster(), trigger.getTriggerEntity());
		action.process(ctx, data, condition().evaluate(ctx));
	}

	public void processTargetEntity(SkillTargetingData targeting, SkillTargetEntityData data) {
		EvaluationContext ctx = EvaluationContext.ofTarget(data.caster(), data.target());
		action.process(ctx, data, condition.evaluate(ctx));
	}

	public void processTargetBlock(SkillTargetingData targeting, SkillTargetBlockData data) {
		EvaluationContext ctx = EvaluationContext.ofTargetPos(data.caster(), data.pos());

	}
}
