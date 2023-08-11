package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;

public interface ISkillCondition {

	boolean evaluate(EvaluationContext ctx);

}
