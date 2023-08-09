package dev.xkmc.ymlparser.type;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IterableType<T> extends DataType<T> {

	Collection<String> getAllKeys();

	@Nullable
	SupplierEntry<T> get(String config);

	@Override
	default T parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() != 1 || !(elem.list.get(0) instanceof StringElement.StrElem str)) {
			logger.fatal("value should be a simple value, found " + elem);
			throw new IllegalStateException("unreachable");
		}
		String val = str.toString();
		SupplierEntry<T> entry = get(val);
		if (entry == null) {
			logger.fatal("value " + val + " is not valid for type " + name());
			throw new IllegalStateException("unreachable");
		}
		return entry.parse();
	}

	interface SupplierEntry<T> {

		T parse();

	}

}
