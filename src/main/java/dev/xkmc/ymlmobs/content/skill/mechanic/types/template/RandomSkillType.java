package dev.xkmc.ymlmobs.content.skill.mechanic.types.template;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.type.YMDataTypeRegistry;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;
import dev.xkmc.ymlparser.type.DataType;

public record RandomSkillType(String name) implements DataType<RandomSkillEntry> {

	@Override
	public RandomSkillEntry parse(ParserLogger logger, StringElement.ListElem elem) {
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.Hierarchy hier) {
			if (hier.hierarchy == StringHierarchy.BRACKET) {
				SkillMechanic mechanic = YMDataTypeRegistry.MECHANIC.parse(logger, hier.list.get(0));
				double weight = DataTypeMetaRegistries.DOUBLE.parseStatic(logger, hier.list.get(1));
				return new RandomSkillEntry(mechanic, weight);
			}
		}
		return new RandomSkillEntry(YMDataTypeRegistry.MECHANIC.parse(logger, elem), 1);
	}

}
