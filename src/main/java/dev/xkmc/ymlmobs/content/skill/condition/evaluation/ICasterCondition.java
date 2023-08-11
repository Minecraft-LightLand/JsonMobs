package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import net.minecraft.world.entity.LivingEntity;

public interface ICasterCondition {

	boolean check(LivingEntity entity);

}
