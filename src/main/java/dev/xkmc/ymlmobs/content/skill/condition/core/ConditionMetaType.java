package dev.xkmc.ymlmobs.content.skill.condition.core;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.MetaTypeEntry;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class ConditionMetaType extends MetaTypeRegistry<ISkillCondition> {

	public ConditionMetaType(String name) {
		super(name);
	}

	@Override
	public Either<ISkillCondition, MetaTypeEntry<ISkillCondition>> parseType(ParserLogger logger, StringElement.ListElem elem) {
		return Either.left(new BoolParser(logger, elem).parse());
	}

	public ISkillCondition parseRaw(ParserLogger logger, StringElement.ListElem elem) {
		var result = super.parseType(logger, elem);
		if (result.left().isEmpty()) {
			throw logger.fatal(elem.start, "Failed to construct " + name() + " from " + elem);
		}
		return result.left().get();
	}

}
