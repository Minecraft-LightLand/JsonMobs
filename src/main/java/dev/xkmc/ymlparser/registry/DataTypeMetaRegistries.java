package dev.xkmc.ymlparser.registry;

import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.core.DoubleType;
import dev.xkmc.ymlparser.primitive.core.IntType;
import dev.xkmc.ymlparser.primitive.core.StringType;
import dev.xkmc.ymlparser.type.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataTypeMetaRegistries {

	private static final Map<Class<?>, DataType<?>> HANDLERS = new ConcurrentHashMap<>();
	private static final Map<Class<?>, HolderDataType<?>> HOLDERS = new ConcurrentHashMap<>();

	public static <R extends DataType<T>, T> R registerDataType(Class<T> cls, R type) {
		HANDLERS.put(cls, type);
		HOLDERS.put(cls, HolderDataType.wrap(type));
		return type;
	}

	public static <R extends HolderDataTypeImpl<T>, T> R registerHolderType(Class<T> cls, R type) {
		HOLDERS.put(cls, type);
		HANDLERS.put(cls, type.asStaticType());
		return type;
	}

	public static final IntType INT = registerHolderType(Integer.class, new IntType("int"));
	public static final DoubleType DOUBLE = registerHolderType(Double.class, new DoubleType("double"));
	public static final StringType STRING = registerHolderType(String.class, new StringType("String"));

	static {
		regRegistry(Block.class, ForgeRegistries.BLOCKS);
		regRegistry(Item.class, ForgeRegistries.ITEMS);
		regRegistry(Potion.class, ForgeRegistries.POTIONS);
		regRegistry(MobEffect.class, ForgeRegistries.MOB_EFFECTS);
		regRegistry(Enchantment.class, ForgeRegistries.ENCHANTMENTS);
		regRegistry(EntityType.class, ForgeRegistries.ENTITY_TYPES);
		regRegistry(Attribute.class, ForgeRegistries.ATTRIBUTES);
	}

	private static <T extends R, R> void regRegistry(Class<R> cls, IForgeRegistry<T> reg) {
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
		if (cls.isEnum()) {
			return Wrappers.cast(EnumType.of(Wrappers.cast(cls)).asHolder());
		}
		throw new IllegalStateException("failed to find Holder DataType for class " + cls.getSimpleName());
	}
}
