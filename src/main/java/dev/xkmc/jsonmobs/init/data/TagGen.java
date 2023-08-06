package dev.xkmc.jsonmobs.init.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.xkmc.jsonmobs.init.JsonMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class TagGen {

	public static void onBlockTagGen(RegistrateTagsProvider.IntrinsicImpl<Block> pvd) {
	}

	public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
	}

	public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {

	}

	public static TagKey<EntityType<?>> createEntityTag(String id) {
		return TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation(JsonMobs.MODID, id));
	}

}
