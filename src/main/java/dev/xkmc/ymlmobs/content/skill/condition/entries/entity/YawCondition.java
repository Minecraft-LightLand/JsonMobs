package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.world.entity.LivingEntity;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "jaylawl",
		name = "yaw",
		description = "Checks the yaw of the target entity against a range."
)
public class YawCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "yaw",
			aliases = {"y"},
			description = "The yaw to check for"
	)
	public IRange yaw;

	@Override
	public boolean check(LivingEntity e) {
		return this.yaw.test(e.getYRot());
	}

}