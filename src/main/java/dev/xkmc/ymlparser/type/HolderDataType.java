package dev.xkmc.ymlparser.type;

import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.primitive.core.StaticData;

public interface HolderDataType<T> extends DataType<DataHolder<T>> {

	static <T> HolderDataType<T> wrap(DataType<T> type) {
		return new Wrapper<>(type);
	}

	record Wrapper<T>(DataType<T> type) implements HolderDataType<T> {

		@Override
		public String name() {
			return type.name();
		}

		@Override
		public DataHolder<T> parse(ParserLogger logger, StringElement.ListElem elem) {
			return new StaticData<>(type.parse(logger, elem));
		}
	}

}
