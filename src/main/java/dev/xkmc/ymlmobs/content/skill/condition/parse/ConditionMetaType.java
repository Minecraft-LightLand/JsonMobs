package dev.xkmc.ymlmobs.content.skill.condition.parse;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.condition.core.ISkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
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

	@Override
	protected void checkValidity(MetaTypeEntry<ISkillCondition> entry) {
		super.checkValidity(entry);
		ConditionType type = entry.getClass().getAnnotation(ConditionType.class);
		if (type == null) {
			throw new IllegalStateException("Condition " + entry.id() + " has no type specified");
		}
		for (var et : type.value()) {
			if (!et.getTypeClass().isAssignableFrom(entry.val())) {
				throw new IllegalStateException("Condition " + entry.id() + " does not implement its type");
			}
		}
	}

}
