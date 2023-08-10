package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.type.DataType;

import java.util.function.BiConsumer;

public interface IArgumentProvider extends ParserLogger {

	void handleNamed(BiConsumer<String, Entry> filler);

	Entry pollUnnamed(String str);

	interface Entry extends ParserLogger {

		<T> T parseValue(DataType<T> dataType);

	}

}
