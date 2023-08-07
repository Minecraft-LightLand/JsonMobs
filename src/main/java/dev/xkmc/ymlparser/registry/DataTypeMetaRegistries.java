package dev.xkmc.ymlparser.registry;

import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.type.DataType;
import dev.xkmc.ymlparser.type.EnumType;
import dev.xkmc.ymlparser.type.ForgeRegistryType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataTypeMetaRegistries {

	private static final Map<Class<?>, DataType<?>> HANDLERS = new ConcurrentHashMap<>();

	static {
		regRegistry(Block.class, ForgeRegistries.BLOCKS);
		regRegistry(Item.class, ForgeRegistries.ITEMS);
		regRegistry(Potion.class, ForgeRegistries.POTIONS);
		regRegistry(MobEffect.class, ForgeRegistries.MOB_EFFECTS);
	}

	private static <T> void regRegistry(Class<T> cls, IForgeRegistry<T> reg) {
		HANDLERS.put(cls, new ForgeRegistryType<>(cls, reg));
	}

	public static DataType<?> get(Class<?> type) {
		if (HANDLERS.containsKey(type)) {
			return HANDLERS.get(type);
		}
		if (type.isEnum()) {
			return EnumType.of(Wrappers.cast(type));
		}
		throw new IllegalStateException("failed to find DataType for class " + type.getSimpleName());
	}

}
