package dev.xkmc.ymlparser.type;

import dev.xkmc.ymlparser.argument.EntryBuilder;
import dev.xkmc.ymlparser.argument.HierarchyArgumentFiller;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;

import java.util.List;

public abstract class MetaDataType<T, R> implements DataType<T> {

	private final MetaTypeRegistry<R> type;

	protected MetaDataType(MetaTypeRegistry<R> type) {
		this.type = type;
	}

	@Override
	public String name() {
		return type.name();
	}

	@Override
	public T parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.Hierarchy hier) {
			if (hier.hierarchy == StringHierarchy.NONE) {
				if (hier.list.size() == 0) {
					throw logger.fatal(hier.start, "No skill config found for " + hier);
				}
				var skill = type.parseType(logger, hier.list.get(0));
				return skill.map(l -> createWithParams(logger, l, hier.list.subList(1, hier.list.size())),
						r -> createWithParams(logger, EntryBuilder.build(r.val(), HierarchyArgumentFiller.of(logger,
										hier.list.subList(1, r.requiredParamCount() + 1))),
								hier.list.subList(r.requiredParamCount() + 1, hier.list.size())));
			}
		}
		var skill = type.parseType(logger, elem);
		if (skill.left().isPresent()) {
			return createSimple(skill.left().get());
		}
		throw logger.fatal(elem.start, "Failed to construct skill for" + elem);
	}

	protected abstract T createSimple(R type);

	protected abstract T createWithParams(ParserLogger logger, R type, List<StringElement.ListElem> other);

}
