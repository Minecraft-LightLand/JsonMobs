package dev.xkmc.ymlparser.type;

import com.mojang.datafixers.util.Either;
import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.argument.ArgumentClassCache;
import dev.xkmc.ymlparser.argument.EntryBuilder;
import dev.xkmc.ymlparser.argument.HierarchyArgumentFiller;
import dev.xkmc.ymlparser.argument.Singleton;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.registry.DataTypeRegistry;

public class MetaTypeRegistry<R> extends DataTypeRegistry<Class<? extends R>, MetaTypeEntry<R>> implements DataType<R> {

	public MetaTypeRegistry(String name) {
		super(name);
	}

	private Either<R, MetaTypeEntry<R>> parseTypePlain(ParserLogger logger, StringElement.StrElem elem) {
		String[] str = elem.toString().split(":");
		MetaTypeEntry<R> skillEntry = get(str[0]);
		if (skillEntry == null) {
			throw logger.fatal(elem.start, "String does not represent a valid " + name() + ": " + elem);
		}
		if (skillEntry.requiredParamCount() > 0) {
			return Either.right(skillEntry);
		}
		if (str.length == 1) {
			return Either.left(skillEntry.construct(logger));
		}
		if (str.length == 2) {
			return Either.left(skillEntry.constructWithStringParam(logger, elem.start + str[0].length() + 1, str[1]));
		}
		throw logger.fatal(elem.start, "More than one colon detected in " + name() + " type: " + elem);
	}

	private Either<R, MetaTypeEntry<R>> parseTypeWithParam(ParserLogger logger, StringElement.StrElem str, StringElement.Hierarchy elems) {
		MetaTypeEntry<R> entry = get(str.toString());
		if (entry == null) {
			throw logger.fatal(str.start, "String does not represent a valid " + name() + ": " + str);
		}
		if (elems.list.size() == 0) {
			if (entry.requiredParamCount() == 0) {
				return Either.left(entry.construct(logger));
			}
			return Either.right(entry);
		}
		return Either.left(EntryBuilder.build(entry.val(), HierarchyArgumentFiller.of(logger, elems.list)));
	}

	public Either<R, MetaTypeEntry<R>> parseType(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() == 1) {
			StringElement strElem = elem.list.get(0);
			if (strElem instanceof StringElement.StrElem str) {
				return parseTypePlain(logger, str);
			}
		} else {
			if (elem.list.size() == 2) {
				if (elem.list.get(0) instanceof StringElement.StrElem str &&
						elem.list.get(1) instanceof StringElement.Hierarchy hier &&
						hier.hierarchy == StringHierarchy.CURVE) {
					return parseTypeWithParam(logger, str, hier);
				}
			}
		}
		throw logger.fatal(elem.start, "String does not represent a valid " + name() + " configuration: " + elem);
	}

	@Override
	public R parse(ParserLogger logger, StringElement.ListElem elem) {
		var result = parseType(logger, elem);
		if (result.left().isEmpty()) {
			throw logger.fatal(elem.start, "Failed to construct " + name() + " from " + elem);
		}
		return result.left().get();
	}

	@Override
	protected void checkValidity(MetaTypeEntry<R> entry) {
		super.checkValidity(entry);
		var list = Wrappers.get(ArgumentClassCache.get(entry.val())::getArguments);
		assert list != null;
		if ((list.size() == 0) == ((entry.val().getAnnotation(Singleton.class) == null))) {
			throw new IllegalStateException("Entry " + entry.descID() + " has wrong singleton attribute");
		}
	}

}
