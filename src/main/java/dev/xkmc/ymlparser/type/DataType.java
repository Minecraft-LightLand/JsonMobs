package dev.xkmc.ymlparser.type;

import dev.xkmc.l2serial.serialization.type_cache.TypeInfo;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;

import java.util.Collection;

public interface DataType<T> {

	static DataType<?> of(TypeInfo type) {
		if (type.getAsClass() == DataHolder.class) {
			return DataTypeMetaRegistries.getHolder(type.getGenericType(0).getAsClass());
		}
		if (Collection.class.isAssignableFrom(type.getAsClass())) {
			return new CollectionDataType<>(of(type.getGenericType(0)));
		}
		return DataTypeMetaRegistries.get(type.getAsClass());
	}

	String name();

	T parse(ParserLogger logger, StringElement.ListElem elem);

}
