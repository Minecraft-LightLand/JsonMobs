package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;

public abstract class SkillCondition implements ISkillCondition {

	@Override
	public final boolean evaluate(EvaluationContext ctx) {
		return false;//TODO
	}

}
