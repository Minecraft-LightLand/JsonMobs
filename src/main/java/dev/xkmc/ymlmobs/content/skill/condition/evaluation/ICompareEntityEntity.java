package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.core.execution.SkillCaster;
import net.minecraft.world.entity.LivingEntity;

public interface ICompareEntityEntity {

	boolean check(SkillCaster entity, EntityDataContext targetEntity);

}
