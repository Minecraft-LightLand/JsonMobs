package dev.xkmc.ymlparser.registry;

import dev.xkmc.ymlparser.type.IterableType;

public interface IDataRegistryEntry<T> extends IterableType.SupplierEntry<T> {

	String id();

	T val();

	String[] alias();

	default T parse() {
		return val();
	}

}
