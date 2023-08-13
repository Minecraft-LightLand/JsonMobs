package dev.xkmc.ymlmobs.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public record LevelPosYaw(Level level, double x, double y, double z, double yaw, double pitch) {

	public static LevelPosYaw of(Entity le) {
		return new LevelPosYaw(le.level(), le.getX(), le.getY(), le.getZ(), le.getXRot(), le.getYRot());
	}

	public static LevelPosYaw of(Level level, BlockPos pos) {
		return new LevelPosYaw(level, pos.getX(), pos.getY(), pos.getZ(), 0, 0);
	}

	public static LevelPosYaw of(Level level, Vec3 pos) {
		return new LevelPosYaw(level, pos.x(), pos.y(), pos.z(), 0, 0);
	}

	public BlockPos asBlockPos() {
		return BlockPos.containing(x, y, z);
	}

	public Vec3 asVec() {
		return new Vec3(x, y, z);
	}

	public LevelPosYaw offset(double dx, double dy, double dz) {
		return new LevelPosYaw(level, x + dx, y + dy, z + dz, yaw, pitch);
	}

	public LevelPosYaw offset(Vec3 v) {
		return offset(v.x, v.y, v.z);
	}

	public BlockState getBlock() {
		return level.getBlockState(asBlockPos());
	}

	public double distance(LevelPosYaw pos) {
		return asVec().distanceTo(pos.asVec());
	}

	public double distanceSqr(LevelPosYaw pos) {
		double d0 = pos.x - this.x;
		double d1 = pos.y - this.y;
		double d2 = pos.z - this.z;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	public LevelPosYaw moveForward(Vec3 v) {
		return offset(rotateVector(v));
	}

	public Vec3 rotateVector(Vec3 v) {
		double angle = yaw * (Math.PI / 180.0);
		double sinyaw = Math.sin(angle);
		double cosyaw = Math.cos(angle);
		angle = pitch * (Math.PI / 180.0);
		double sinpitch = Math.sin(angle);
		double cospitch = Math.cos(angle);
		double newx = 0.0;
		double newy = 0.0;
		double newz = 0.0;
		newz -= v.x * cosyaw;
		newz -= v.y * sinyaw * sinpitch;
		newz -= v.z * sinyaw * cospitch;
		newx += v.x * sinyaw;
		newx -= v.y * cosyaw * sinpitch;
		newx -= v.z * cosyaw * cospitch;
		newy += v.y * cospitch;
		newy -= v.z * sinpitch;
		return new Vec3(newx, newy, newz);
	}

}
