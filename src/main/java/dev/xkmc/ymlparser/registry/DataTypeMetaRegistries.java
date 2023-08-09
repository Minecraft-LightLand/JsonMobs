package dev.xkmc.ymlparser.registry;

import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.core.DoubleType;
import dev.xkmc.ymlparser.primitive.core.IntType;
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
	private static final Map<Class<?>, DataType<?>> HOLDERS = new ConcurrentHashMap<>();

	public static <T> void registerDataType(Class<T> cls, DataType<T> type) {
		HANDLERS.put(cls, type);
	}

	public static <T> void registerHolderType(Class<T> cls, DataType<DataHolder<T>> type) {
		HOLDERS.put(cls, type);
	}

	static {
		registerHolderType(Integer.class, new IntType("int"));
		registerHolderType(Double.class, new DoubleType("double"));

		regRegistry(Block.class, ForgeRegistries.BLOCKS);
		regRegistry(Item.class, ForgeRegistries.ITEMS);
		regRegistry(Potion.class, ForgeRegistries.POTIONS);
		regRegistry(MobEffect.class, ForgeRegistries.MOB_EFFECTS);
	}

	private static <T> void regRegistry(Class<T> cls, IForgeRegistry<T> reg) {
		registerDataType(cls, new ForgeRegistryType<>(cls, reg));
	}

	public static <T> DataType<T> get(Class<T> type) {
		if (HANDLERS.containsKey(type)) {
			return Wrappers.cast(HANDLERS.get(type));
		}
		if (type.isEnum()) {
			return Wrappers.cast(EnumType.of(Wrappers.cast(type)));
		}
		throw new IllegalStateException("failed to find DataType for class " + type.getSimpleName());
	}

	public static <T> DataType<DataHolder<T>> getHolder(Class<T> cls) {
		if (HOLDERS.containsKey(cls)) {
			return Wrappers.cast(HOLDERS.get(cls));
		}
		throw new IllegalStateException("failed to find Holder DataType for class " + cls.getSimpleName());
	}
}
