package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Phil",
		name = "IsLeashed",
		description = "If the target is leashed"
)
public class IsLeashedCondition extends SkillCondition implements IEntityCondition {

	@Override
	public boolean check(LivingEntity e) {
		return e instanceof Mob mob && mob.isLeashed();
	}

}
