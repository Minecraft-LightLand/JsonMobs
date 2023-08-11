package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import net.minecraft.world.entity.LivingEntity;

public interface IEntityCondition {

	boolean check(LivingEntity entity);

}
