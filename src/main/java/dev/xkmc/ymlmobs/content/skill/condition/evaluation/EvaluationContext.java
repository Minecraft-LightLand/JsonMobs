package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.world.entity.LivingEntity;

public interface EvaluationContext {

	LivingEntity getCaster();

	LivingEntity getTargetEntity();

	LevelPosYaw getTargetPos();

}
