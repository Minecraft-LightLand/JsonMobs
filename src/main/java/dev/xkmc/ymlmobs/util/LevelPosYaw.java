package dev.xkmc.ymlmobs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public record LevelPosYaw(ResourceLocation world, double x, double y, double z, double yaw, double pitch) {

	public static LevelPosYaw of(LivingEntity le) {
		return new LevelPosYaw(le.level().dimension().location(), le.getX(), le.getY(), le.getZ(), le.getXRot(), le.getYRot());
	}

}
