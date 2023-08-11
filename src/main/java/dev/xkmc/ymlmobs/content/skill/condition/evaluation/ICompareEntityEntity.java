package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import net.minecraft.world.entity.LivingEntity;

public interface ICompareEntityEntity {

	boolean check(LivingEntity entity, LivingEntity targetEntity);

}
