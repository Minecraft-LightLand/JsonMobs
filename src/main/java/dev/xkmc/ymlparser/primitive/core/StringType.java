package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.primitive.variable.StringVariable;
import dev.xkmc.ymlparser.primitive.variable.VariableContext;
import dev.xkmc.ymlparser.type.HolderDataTypeImpl;

import java.util.ArrayList;
import java.util.List;

public class StringType extends HolderDataTypeImpl<String> {

	public StringType(String name) {
		super(name);
	}

	@Override
	public String parseStatic(ParserLogger logger, StringElement.ListElem elem) {
		return null;//TODO
	}

	@Override
	public DataHolder<String> parse(ParserLogger logger, StringElement.ListElem elem) {
		elem = elem.tryUnwrap(StringHierarchy.Type.STRING);
		StringBuilder builder = new StringBuilder();
		List<StringVariable> vars = new ArrayList<>();
		for (var e : elem.list) {
			if (e instanceof StringElement.Hierarchy hier) {
				if (hier.list.size() == 1) {
					StringElement.ListElem l0 = hier.list.get(0);
					if (l0.list.size() == 1) {
						if (l0.list.get(0) instanceof StringElement.StrElem sl) {
							String str = sl.toString();
							if (str.startsWith("&")) {
								builder.append(StringPlaceholderHelper.parse(ParserLogger.of(logger, sl), str));
								continue;
							}
						}
					}
				}
				StringVariable var = VariableContext.of(hier.list);
				if (var == null) {
					logger.error(hier.start, "Unrecognized variable " + hier);
				} else {
					vars.add(var);
					builder.append("%s");
				}
			} else {
				builder.append(e);
			}
		}
		if (vars.size() == 0) {
			return new StaticData<>(builder.toString());
		}
		return new StringExp(builder.toString(), vars);
	}

	record StringExp(String exp, List<StringVariable> vars) implements DataHolder<String> {

		@Override
		public String get(DataContext meta) {
			Object[] args = new String[vars.size()];
			for (int i = 0; i < vars.size(); i++) {
				args[i] = vars.get(i).parse(meta);
			}
			return exp.formatted(args);
		}

	}

}
