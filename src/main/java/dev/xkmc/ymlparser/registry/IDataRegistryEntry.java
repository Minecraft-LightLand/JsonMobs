package dev.xkmc.ymlparser.registry;

import dev.xkmc.l2library.base.L2Registrate;
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

	default void desc(L2Registrate reg, String desc) {
		reg.addRawLang(descID(), desc);
	}

}
