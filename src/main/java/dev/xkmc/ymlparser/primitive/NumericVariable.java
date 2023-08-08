package dev.xkmc.ymlparser.primitive;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.parser.line.StringElement;

import javax.annotation.Nullable;
import java.util.List;

public interface NumericVariable {

	@Nullable
	static NumericVariable of(List<StringElement.ListElem> list) {
		return null;//TODO
	}

	double parse(DataContext meta);

}
