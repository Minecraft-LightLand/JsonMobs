package dev.xkmc.ymlmobs.content.skill.condition.parse;

import dev.xkmc.ymlmobs.content.skill.condition.core.ISkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationContext;

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

		@Override
		public boolean evaluate(EvaluationContext ctx) {
			return !cond.evaluate(ctx);
		}
	}

	record BinOp(boolean equal, ISkillCondition b0, ISkillCondition b1) implements CompositeCondition {

		@Override
		public boolean evaluate(EvaluationContext ctx) {
			return equal == (b0.evaluate(ctx) == b1.evaluate(ctx));
		}
	}

	record ListOp(boolean and, List<ISkillCondition> list) implements CompositeCondition {

		@Override
		public boolean evaluate(EvaluationContext ctx) {
			boolean ans = and;
			for (var c : list) {
				boolean res = c.evaluate(ctx);
				if (and) {
					ans &= res;
				} else {
					ans |= res;
				}
			}
			return ans;
		}

	}

}
