package dev.xkmc.ymlparser.type;

import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.argument.ArgumentClassCache;
import dev.xkmc.ymlparser.argument.ArgumentField;
import dev.xkmc.ymlparser.argument.EntryBuilder;
import dev.xkmc.ymlparser.argument.IArgumentProvider;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.registry.DataTypeLangGen;
import dev.xkmc.ymlparser.registry.IDataRegistryEntry;

import java.util.function.BiConsumer;

public record MetaTypeEntry<T>(MetaTypeRegistry<T> reg, String id, Class<? extends T> val,
							   String... alias) implements IDataRegistryEntry<Class<? extends T>> {

	public T construct(ParserLogger logger) {
		return EntryBuilder.build(val, new NullArgumentProvider(logger.parent()));
	}

	public T constructWithStringParam(ParserLogger logger, int index, String s) {
		return EntryBuilder.build(val, new StringArgumentProvider(logger.parent(), index, s));
	}

	public int requiredParamCount() {
		return EntryBuilder.countRequired(val);
	}

	@Override
	public void desc(DataTypeLangGen reg, String desc) {
		IDataRegistryEntry.super.desc(reg, desc);
		registerArgs(reg, val());
	}

	private static void registerArgs(DataTypeLangGen reg, Class<?> cls) {
		var list = Wrappers.get(ArgumentClassCache.get(cls)::getArguments);
		assert list != null;
		for (ArgumentField field : list) {
			reg.addArg(field);
			if (field.isComplex()) {
				registerArgs(reg, field.arg().compound());
			}
		}
	}

	private record NullArgumentProvider(ParserLogger.File parent)
			implements IArgumentProvider, ParserLogger.Wrapped {

		@Override
		public void handleNamed(BiConsumer<String, Entry> filler) {

		}

		@Override
		public Entry pollUnnamed(String str) {
			throw fatal("Field " + str + " is not filled");
		}
	}

	private record StringArgumentProvider(ParserLogger.File parent, int defaultIndex, String str)
			implements IArgumentProvider, ParserLogger.Sub, IArgumentProvider.Entry {

		@Override
		public void handleNamed(BiConsumer<String, Entry> filler) {

		}

		@Override
		public Entry pollUnnamed(String str) {
			return this;
		}

		@Override
		public <T> T parseValue(DataType<T> type) {
			return type.parse(this, StringElement.wrapSimple(defaultIndex, str));
		}

	}

}
