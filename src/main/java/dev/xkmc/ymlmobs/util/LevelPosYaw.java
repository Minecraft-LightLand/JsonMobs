package dev.xkmc.ymlmobs.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record LevelPosYaw(Level level, double x, double y, double z, double yaw, double pitch) {

	public static LevelPosYaw of(LivingEntity le) {
		return new LevelPosYaw(le.level(), le.getX(), le.getY(), le.getZ(), le.getXRot(), le.getYRot());
	}

	public BlockPos asBlockPos() {
		return BlockPos.containing(x, y, z);
	}

	public Vec3 asVec() {
		return new Vec3(x, y, z);
	}
}
