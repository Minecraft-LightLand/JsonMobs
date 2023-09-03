package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillModifiableData;

public abstract class ConditionAction {

	public abstract void process(EvaluationContext ctx, SkillModifiableData data, boolean result);

	//TODO what is LEVEL?
}
