package dev.xkmc.ymlparser.primitive.core;

import com.mojang.datafixers.util.Pair;
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

	private static Pair<String, List<StringVariable>> parseStr(ParserLogger logger, StringElement.ListElem elem, boolean allowVar) {
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
								builder.append(StringPlaceholderHelper.parse(ParserLogger.of(logger, sl.start), str));
								continue;
							}
						}
					}
				}
				if (allowVar) {
					StringVariable var = VariableContext.of(hier.list);
					if (var == null) {
						logger.error(hier.start, "Unrecognized variable " + hier);
					} else {
						vars.add(var);
						builder.append("%s");
					}
				} else {
					builder.append(hier.toString());
				}
			} else {
				builder.append(e);
			}
		}
		return Pair.of(builder.toString(), vars);
	}

	public StringType(String name) {
		super(name);
	}

	@Override
	public String parseStatic(ParserLogger logger, StringElement.ListElem elem) {
		return parseStr(logger, elem, false).getFirst();
	}

	@Override
	public DataHolder<String> parse(ParserLogger logger, StringElement.ListElem elem) {
		var result = parseStr(logger, elem, true);
		if (result.getSecond().size() == 0) {
			return new StaticData<>(result.getFirst());
		}
		return new StringExp(result.getFirst(), result.getSecond());
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
