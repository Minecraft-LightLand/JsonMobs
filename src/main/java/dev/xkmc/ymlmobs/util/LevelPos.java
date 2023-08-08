package dev.xkmc.ymlmobs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public record LevelPos(ResourceLocation level, double x, double y, double z) {

	public Vec3 pos() {
		return new Vec3(x, y, z);
	}

}
