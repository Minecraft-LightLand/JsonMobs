package dev.xkmc.ymlmobs.content.variables.builtin;

import net.minecraft.world.entity.EntityType;

public interface TargetContext extends EntityContext {

	int threat();

	PosContext l();

	BlockContext block();

	EntityType<?> entity_type();

	int itemstack_amount();

}
