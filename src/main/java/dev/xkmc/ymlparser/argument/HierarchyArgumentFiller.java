package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

import java.util.List;
import java.util.function.BiConsumer;

public record HierarchyArgumentFiller(String file, int line, List<StringElement.ListElem> elem)
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

	public static HierarchyArgumentFiller of(ParserLogger logger, List<StringElement.ListElem> elem) {
		if (logger instanceof HierarchyArgumentFiller filler && filler.elem == elem) return filler;
		return new HierarchyArgumentFiller(logger.parent().file(), logger.parent().line(), elem);
	}

	private ArgEntry of(StringElement.ListElem elem) {
		if (elem.list.isEmpty()) {
			throw fatal("no argument name found at index for string " + elem);
		}
		if (!(elem.list.get(0) instanceof StringElement.StrElem str)) {
			throw fatal("argument should start with parameter name, but it's " + elem.list.get(0).toString());
		}
		String text = str.toString();
		String[] strs = text.split("=");
		if (strs.length != 2) {
			throw fatal("argument should contains '=' sign. Nothing found in " + text);
		}
		String name = strs[0];
		return new ArgEntry(this, name, elem.subElem(name.length() + 1));
	}

	@Override
	public void handleNamed(BiConsumer<String, Entry> filler) {
		for (var e : elem) {
			ArgEntry entry = of(e);
			filler.accept(entry.name, entry);
		}
	}

	@Override
	public Entry pollUnnamed(String str) {
		throw fatal("Field " + str + " is not filled");
	}

	@Override
	public int defaultIndex() {
		return elem.get(0).start;
	}

}
