package dev.xkmc.ymlparser.registry;

import dev.xkmc.ymlparser.type.IterableType;

public record DataRegistryEntry<T>(String id, T val, String... alias) implements IterableType.SupplierEntry<T> {

	@Override
	public T parse() {
		return val;
	}

}
