package dev.xkmc.ymlparser.type;

import dev.xkmc.l2serial.serialization.type_cache.TypeInfo;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.ConfigParser;
import dev.xkmc.ymlparser.parser.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;

import javax.annotation.Nullable;
import java.util.Collection;

public interface DataType<T> {

	static DataType<?> of(TypeInfo type) {
		if (type.getAsClass() == DataHolder.class) {
			return DataTypeMetaRegistries.get(type.getGenericType(0).getAsClass());
		}
		if (Collection.class.isAssignableFrom(type.getAsClass())) {
			return new CollectionDataType<>(of(type.getGenericType(0)));
		}
		return DataTypeMetaRegistries.get(type.getAsClass());
	}

	String name();

	@Nullable
	DataEntry<T> get(String config);

	T parse(ParserLogger logger, StringElement.ListElem elem);

}
