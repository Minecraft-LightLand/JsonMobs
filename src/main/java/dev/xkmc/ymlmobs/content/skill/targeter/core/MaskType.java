package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.compound.CompoundValue;
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

	public static class Builder extends CompoundValue<Builder, MaskType> {

		@Argument(name = "noair", aliases = "na", optional = true,
				description = "ignore air blocks and replaceable blocks")
		public boolean noAir = true;

		@Argument(name = "onlyair", aliases = "oa", optional = true,
				description = "select only air")
		public boolean onlyAir = false;

		public MaskType build() {
			if (noAir && !onlyAir) {
				return MaskType.IGNORE_AIR;
			} else if (onlyAir) {
				return MaskType.ONLY_AIR;
			} else {
				return MaskType.ALL;
			}
		}

	}

}