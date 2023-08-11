package dev.xkmc.ymlparser.registry;

public record DataRegistryEntry<T>(DataTypeRegistry<T, ?> reg, String id, T val,
								   String[] alias) implements IDataRegistryEntry<T> {

}
