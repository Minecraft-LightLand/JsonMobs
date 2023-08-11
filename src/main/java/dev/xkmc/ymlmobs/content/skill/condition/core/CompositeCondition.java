package dev.xkmc.ymlmobs.content.skill.condition.core;

import java.util.List;

public interface CompositeCondition extends ISkillCondition {

	static ISkillCondition inverse(ISkillCondition cond) {
		return new NotOp(cond);
	}

	static ISkillCondition listOp(BoolOp op, List<ISkillCondition> list) {
		return new ListOp(op == BoolOp.AND, list);
	}

	static ISkillCondition binary(BoolOp op, ISkillCondition c0, ISkillCondition c1) {
		return new BinOp(op == BoolOp.EQ, c0, c1);
	}

	record NotOp(ISkillCondition cond) implements CompositeCondition {

	}

	record BinOp(boolean equal, ISkillCondition b0, ISkillCondition b1) implements CompositeCondition {

	}

	record ListOp(boolean and, List<ISkillCondition> list) implements CompositeCondition {

	}

}
