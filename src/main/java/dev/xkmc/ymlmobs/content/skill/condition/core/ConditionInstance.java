package dev.xkmc.ymlmobs.content.skill.condition.core;

import dev.xkmc.ymlmobs.content.skill.condition.action.RequiredAction;
import dev.xkmc.ymlparser.argument.EntryBuilder;

public class ConditionInstance {

	public static ConditionInstance of(ISkillCondition cond, ConditionAction action) {
		return new ConditionInstance(cond, action);
	}

	public static ConditionInstance of(ISkillCondition cond, boolean action) {
		return new ConditionInstance(cond, action ? EntryBuilder.create(RequiredAction.class) : EntryBuilder.create(ConditionAction.class));
	}

	private final ISkillCondition condition;
	private final ConditionAction action;

	private ConditionInstance(ISkillCondition condition, ConditionAction action) {
		this.condition = condition;
		this.action = action;
	}

}
