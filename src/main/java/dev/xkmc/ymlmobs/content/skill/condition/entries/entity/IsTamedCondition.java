package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;
import net.minecraft.world.entity.TamableAnimal;

@ConditionType(
		type = EvaluationType.ENTITY,
		name = "isTamed",
		description = "Checks whether the target is tamed"
)
public class IsTamedCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get() instanceof TamableAnimal tame && tame.isTame();
	}
}
