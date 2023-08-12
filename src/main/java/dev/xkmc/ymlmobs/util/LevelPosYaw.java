package dev.xkmc.ymlmobs.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record LevelPosYaw(Level level, double x, double y, double z, double yaw, double pitch) {

	public static LevelPosYaw of(Entity le) {
		return new LevelPosYaw(le.level(), le.getX(), le.getY(), le.getZ(), le.getXRot(), le.getYRot());
	}

	public static LevelPosYaw of(Level level, BlockPos pos) {
		return new LevelPosYaw(level, pos.getX(), pos.getY(), pos.getZ(), 0, 0);
	}

	public BlockPos asBlockPos() {
		return BlockPos.containing(x, y, z);
	}

	public Vec3 asVec() {
		return new Vec3(x, y, z);
	}
}
