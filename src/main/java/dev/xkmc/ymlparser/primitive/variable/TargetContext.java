package dev.xkmc.ymlparser.primitive.variable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public interface TargetContext extends EntityContext {

	int threat();

	PosContext l();

	BlockContext block();

	EntityType<?> entity_type();

	int itemstack_amount();

}
