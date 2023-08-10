package dev.xkmc.ymlparser.registry;

public record DataRegistryEntry<T>(String id, T val, String... alias) implements IDataRegistryEntry<T> {

}
