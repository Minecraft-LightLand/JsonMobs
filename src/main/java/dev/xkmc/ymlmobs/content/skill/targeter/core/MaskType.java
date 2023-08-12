package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.util.LevelPosYaw;
import net.minecraft.world.level.block.state.BlockState;

public enum MaskType {
	ALL,
	IGNORE_AIR,
	ONLY_AIR;

	public boolean test(LevelPosYaw pos) {
		if (this == ALL) {
			return true;
		}
		BlockState state = pos.getBlock();
		return (this == ONLY_AIR) == (state.canBeReplaced() && state.getFluidState().isEmpty());
	}

}