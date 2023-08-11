package dev.xkmc.ymlparser.registry;

import dev.xkmc.ymlparser.type.IterableType;

public interface IDataRegistryEntry<T> extends IterableType.SupplierEntry<T> {

	DataTypeRegistry<T, ?> reg();

	String id();

	T val();

	String[] alias();

	default String descID() {
		return reg().name() + "." + id();
	}

	default T parse() {
		return val();
	}

	default void desc(DataTypeLangGen reg, String desc) {
		reg.addLang(descID(), desc);
	}

}
