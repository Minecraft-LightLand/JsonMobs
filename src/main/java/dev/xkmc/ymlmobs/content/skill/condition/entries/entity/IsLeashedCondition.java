package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import net.minecraft.world.entity.Mob;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Phil",
		name = "IsLeashed",
		description = "If the target is leashed"
)
public class IsLeashedCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(EntityDataContext e) {
		return e.get() instanceof Mob mob && mob.isLeashed();
	}

}
