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
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.StrElem str) {
			String[] strs = str.toString().split(",");
			List<T> ans = new ArrayList<>();
			int index = 0;
			for (String s : strs) {
				ans.add(type.parse(logger, StringElement.wrapSimple(str.start + index, s)));
				index += s.length() + 1;
			}
			return ans;
		}
		if (elem.list.size() != 1 || !(elem.list.get(0) instanceof StringElement.Hierarchy hier) || hier.hierarchy != StringHierarchy.BRACKET) {
			throw logger.fatal("value should be a list value, found " + elem);
		}
		List<T> ans = new ArrayList<>();
		for (var e : hier.list) {
			if (type instanceof MetaDataType<T, ?> meta) {
				ans.add(meta.parse(logger, e));
			} else {
				ans.add(type.parse(logger, e.tryUnwrap(StringHierarchy.Type.NONE)));
			}
		}
		return ans;
	}

}
