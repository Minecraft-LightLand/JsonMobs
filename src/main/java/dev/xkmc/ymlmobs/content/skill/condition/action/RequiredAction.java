package dev.xkmc.ymlmobs.content.skill.condition.action;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionAction;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillModifiableData;
import dev.xkmc.ymlparser.argument.Singleton;

@Singleton
public class RequiredAction extends ConditionAction {

	@Override
	public void process(EvaluationContext ctx, SkillModifiableData data, boolean result) {
		if (!result) {
			data.remove();
		}
	}
}
