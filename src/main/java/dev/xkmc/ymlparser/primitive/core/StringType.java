package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.type.DataType;

public class StringType implements DataType<DataHolder<String>> {

	@Override
	public String name() {
		return "String";
	}

	@Override
	public DataHolder<String> parse(ParserLogger logger, StringElement.ListElem elem) {
		elem = elem.tryUnwrap(StringHierarchy.Type.STRING);
		StringBuilder builder = new StringBuilder();
		for (var e : elem.list) {
			if (e instanceof StringElement.Hierarchy hier) {
				if (hier.list.size() == 1) {
					var l0 = hier.list.get(0);
					if (l0.list.size() == 1) {
						if (l0.list.get(0) instanceof StringElement.StrElem sl) {
							String str = sl.toString();
							if (str.startsWith("&")) {
								builder.append(StringPlaceholderHelper.parse(ParserLogger.of(logger, sl), str));
								continue;
							}
						}
					}
				}
				builder.append("{}");
			} else {
				builder.append(e.toString());
			}
		}
		return null;
	}
}
