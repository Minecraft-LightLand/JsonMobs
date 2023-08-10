package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;
import dev.xkmc.ymlparser.type.HolderDataTypeImpl;

import javax.annotation.Nullable;
import java.util.Locale;

public class BooleanType extends HolderDataTypeImpl<Boolean> {

	public BooleanType(String name) {
		super(name);
	}

	@Nullable
	private static Boolean parseSimple(String str) {
		str = str.toLowerCase(Locale.ROOT);
		if (str.equals("1") || str.equals("t") || str.equals("true")) {
			return true;
		}
		if (str.equals("0") || str.equals("f") || str.equals("false")) {
			return false;
		}
		return null;
	}


	@Override
	public DataHolder<Boolean> parse(ParserLogger logger, StringElement.ListElem elem) {
		elem = elem.tryUnwrap(StringHierarchy.Type.STRING);
		if (elem.list.size() == 1 && elem.list.get(0) instanceof StringElement.StrElem str) {
			String num = str.toString();
			Boolean ans = parseSimple(num);
			if (ans != null) return new StaticData<>(ans);
		}
		return new BooleanHolder(DataTypeMetaRegistries.DOUBLE.parse(logger, elem));
	}

	@Override
	public Boolean parseStatic(ParserLogger logger, StringElement.ListElem elem) {
		Boolean ans = parseSimple(elem.toString());
		if (ans == null) {
			logger.error(elem.start, "Failed to parse boolean from " + elem + ", default to false");
			return false;
		}
		return ans;
	}

	private record BooleanHolder(DataHolder<Double> val) implements DataHolder<Boolean> {

		@Override
		public Boolean get(DataContext meta) {
			return val.get(meta) > 0.5;
		}
	}

}
