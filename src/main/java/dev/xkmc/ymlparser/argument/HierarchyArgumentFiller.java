package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlparser.parser.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

import java.util.function.BiConsumer;

public record HierarchyArgumentFiller(String file, int line, StringElement.Hierarchy elem)
		implements IArgumentProvider, ParserLogger.File {

	private record ArgEntry(HierarchyArgumentFiller parent, String name, StringElement.ListElem elem)
			implements Entry, ParserLogger.Sub {

		@Override
		public <T> T parseValue(DataType<T> dataType) {
			return dataType.parse(this, elem);
		}

		@Override
		public int defaultIndex() {
			return elem.start;
		}
	}

	private ArgEntry of(StringElement.ListElem elem) {
		if (elem.list.isEmpty()) {
			fatal("no argument name found at index for string " + elem);
		}
		if (!(elem.list.get(0) instanceof StringElement.StrElem str)) {
			fatal("argument should start with parameter name, but it's " + elem.list.get(0).toString());
			throw new IllegalStateException("unreachable");
		}
		String text = str.toString();
		String[] strs = text.split("=");
		if (strs.length != 2) {
			fatal("argument should contains '=' sign. Nothing found in " + text);
		}
		String name = strs[0];
		return new ArgEntry(this, name, elem.subElem(name.length() + 1));
	}

	@Override
	public void handle(BiConsumer<String, Entry> filler) {
		for (var e : elem.list) {
			ArgEntry entry = of(e);
		}
	}

	@Override
	public int defaultIndex() {
		return elem.start;
	}

}
