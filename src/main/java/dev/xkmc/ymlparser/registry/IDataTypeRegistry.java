package dev.xkmc.ymlparser.registry;

import java.util.Collection;

public interface IDataTypeRegistry<T> {

	String name();

	Collection<String> getAllKeys();

}
