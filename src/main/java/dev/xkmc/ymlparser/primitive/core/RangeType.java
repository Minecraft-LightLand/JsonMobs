package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import dev.xkmc.ymlparser.primitive.calc.NumberParser;
import dev.xkmc.ymlparser.type.DataType;

public class RangeType implements DataType<IRange> {

	private final String name;

	public RangeType(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public IRange parse(ParserLogger logger, StringElement.ListElem elem) {
		var ans = NumberParser.parseRange(elem.toString());
		if (ans == null) {
			throw logger.fatal(elem.start, "literal " + elem + " does not represent a valid number range");
		}
		return ans;
	}
}
