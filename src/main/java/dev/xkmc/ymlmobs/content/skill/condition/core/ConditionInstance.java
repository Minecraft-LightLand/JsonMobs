package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.action.RequiredAction;
import dev.xkmc.ymlparser.argument.EntryBuilder;

public record ConditionInstance(ISkillCondition condition, ConditionAction action) {

	public static ConditionInstance of(ISkillCondition cond, ConditionAction action) {
		return new ConditionInstance(cond, action);
	}

	public static ConditionInstance of(ISkillCondition cond, boolean action) {
		return new ConditionInstance(cond, action ? EntryBuilder.create(RequiredAction.class) : EntryBuilder.create(ConditionAction.class));
	}

}
