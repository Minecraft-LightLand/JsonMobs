package dev.xkmc.ymlparser.type;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record CollectionDataType<T>(DataType<T> type) implements DataType<Collection<T>> {

	@Override
	public String name() {
		return "Collection[%s]".formatted(type.name());
	}

	@Override
	public Collection<T> parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() != 1 || !(elem.list.get(0) instanceof StringElement.Hierarchy hier) || hier.hierarchy != StringHierarchy.B) {
			logger.fatal("value should be a list value, found " + elem);
			throw new IllegalStateException("unreachable");
		}
		List<T> ans = new ArrayList<>();
		for (var e : hier.list) {
			ans.add(type.parse(logger, e));
		}
		return ans;
	}

}
