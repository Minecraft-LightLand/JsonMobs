package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.MetaSkill;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.MetaDataType;

public class MetaSkillType implements MetaDataType<MetaSkill> {

	@Override
	public String name() {
		return null;
	}

	@Override
	public MetaSkill parse(ParserLogger logger, StringElement.ListElem elem) {
		int size = elem.list.size();
		return null;
	}

}
