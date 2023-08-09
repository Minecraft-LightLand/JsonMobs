package dev.xkmc.ymlparser.primitive.variable;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.parser.line.StringElement;

import javax.annotation.Nullable;
import java.util.List;

public interface NumericVariable {

	double parse(DataContext meta);

}
