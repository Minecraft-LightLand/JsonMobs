package dev.xkmc.ymlparser.parser.core;

import dev.xkmc.ymlmobs.init.YmlMobs;
import dev.xkmc.ymlparser.parser.line.StringElement;

public interface ParserLogger {

	static ParserLogger of(ParserLogger logger, StringElement elem) {
		while (logger instanceof Sub sub) {
			logger = sub.parent();
		}
		return new SubElem(logger, elem);
	}

	void error(String s);

	void error(int i, String s);

	void fatal(String s);

	void fatal(int index, String s);

	interface Line extends ParserLogger {

		int defaultIndex();

		@Override
		default void error(String s) {
			error(defaultIndex(), s);
		}

		@Override
		default void fatal(String s) {
			fatal(defaultIndex(), s);
		}

	}

	interface Sub extends Line {

		ParserLogger parent();

		@Override
		default void error(int i, String s) {
			parent().error(i, s);
		}

		@Override
		default void fatal(int i, String s) {
			parent().fatal(i, s);
		}

	}

	interface File extends Line {

		String file();

		int line();

		@Override
		default void error(int i, String s) {
			YmlMobs.LOGGER.error("Error occurs in file %s line %d column %d: ".formatted(file(), line(), i) + s);
		}

		@Override
		default void fatal(int index, String s) {
			YmlMobs.LOGGER.fatal("Fatal error occurs in file %s line %d column %d: ".formatted(file(), line(), index) + s);
			throw new IllegalArgumentException(s);
		}

	}

	record SubElem(ParserLogger parent, StringElement elem) implements Sub {

		@Override
		public int defaultIndex() {
			return elem.start;
		}
	}

}
