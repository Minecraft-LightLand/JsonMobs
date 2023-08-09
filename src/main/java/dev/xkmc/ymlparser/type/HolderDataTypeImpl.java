package dev.xkmc.ymlparser.type;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;

public abstract class HolderDataTypeImpl<T> implements HolderDataType<T> {

	private final String name;
	private final Static<T> type = new Static<>(this);

	protected HolderDataTypeImpl(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	public abstract T parseStatic(ParserLogger logger, StringElement.ListElem elem);

	public DataType<T> asStaticType() {
		return type;
	}

	record Static<T>(HolderDataTypeImpl<T> type) implements DataType<T> {

		@Override
		public String name() {
			return type.name();
		}

		@Override
		public T parse(ParserLogger logger, StringElement.ListElem elem) {
			return type.parseStatic(logger, elem);
		}

	}

}
