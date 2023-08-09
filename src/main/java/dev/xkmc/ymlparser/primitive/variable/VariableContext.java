package dev.xkmc.ymlparser.primitive.variable;

import dev.xkmc.ymlparser.parser.line.StringElement;

import javax.annotation.Nullable;
import java.util.List;

public interface VariableContext {

	@Nullable
	static NumericVariable ofNumeric(List<StringElement.ListElem> list) {
		return null;//TODO
	}

	@Nullable
	static StringVariable of(List<StringElement.ListElem> list) {
		return null;//TODO
	}

}
