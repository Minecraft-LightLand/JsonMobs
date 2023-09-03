package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "jaylawl",
		name = "pitch",
		description = "Checks if the pitch of the target entity is within a range"
)
public class PitchCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "pitch",
			aliases = {"p"},
			description = "The number range to match"
	)
	public IRange pitch;

	@Override
	public boolean check(EntityDataContext e) {
		return this.pitch.test(e.get().getXRot());
	}

}
