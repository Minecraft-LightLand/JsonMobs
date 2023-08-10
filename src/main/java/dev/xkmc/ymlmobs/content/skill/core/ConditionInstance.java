package dev.xkmc.ymlmobs.content.skill.core;

public class ConditionInstance {

	public static ConditionInstance of(SkillCondition cond, boolean target) {
		return new ConditionInstance(cond, target);
	}

	private final SkillCondition condition;
	private final boolean target;

	private ConditionInstance(SkillCondition condition, boolean target) {
		this.condition = condition;
		this.target = target;
	}

}
