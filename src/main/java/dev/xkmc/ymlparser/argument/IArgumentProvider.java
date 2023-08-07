package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlparser.parser.ParserLogger;
import dev.xkmc.ymlparser.type.DataType;

import java.util.function.BiConsumer;

public interface IArgumentProvider extends ParserLogger {

	void handle(BiConsumer<String, Entry> filler);

	interface Entry extends ParserLogger {

		<T> T parseValue(DataType<T> dataType);

	}

}
