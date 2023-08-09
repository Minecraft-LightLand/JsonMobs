package dev.xkmc.ymlmobs.content.variables.builtin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public interface EntityContext {

	UUID uuid();

	Level level();

	Component name();

	double hp();

	double mhp();

	double php();

	int thp();

	BlockState raytrace();

}
