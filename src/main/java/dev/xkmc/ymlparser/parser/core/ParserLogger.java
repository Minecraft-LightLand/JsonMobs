package dev.xkmc.ymlparser.parser.core;

import dev.xkmc.ymlmobs.init.YmlMobs;

public interface ParserLogger {

	static ParserLogger of(ParserLogger logger, int start) {
		return new SubElem(logger.parent(), start);
	}

	void error(String s);

	void error(int i, String s);

	IllegalArgumentException fatal(String s);

	IllegalArgumentException fatal(int index, String s);

	ParserLogger.File parent();

	interface Wrapped extends ParserLogger {

		@Override
		default void error(String s) {
			parent().error(s);
		}

		@Override
		default void error(int i, String s) {
			parent().error(i, s);
		}

		@Override
		default IllegalArgumentException fatal(String s) {
			return parent().fatal(s);
		}

		@Override
		default IllegalArgumentException fatal(int index, String s) {
			return parent().fatal(index, s);
		}

	}

	interface Line extends ParserLogger {

		int defaultIndex();

		@Override
		default void error(String s) {
			error(defaultIndex(), s);
		}

		@Override
		default IllegalArgumentException fatal(String s) {
			return fatal(defaultIndex(), s);
		}

	}

	interface Sub extends Line {

		@Override
		default void error(int i, String s) {
			parent().error(i, s);
		}

		@Override
		default IllegalArgumentException fatal(int i, String s) {
			return parent().fatal(i, s);
		}

	}

	interface File extends Line {

		@Override
		default File parent() {
			return this;
		}

		String file();

		int line();

		@Override
		default void error(int i, String s) {
			YmlMobs.LOGGER.error("Error occurs in file %s line %d column %d: ".formatted(file(), line(), i) + s);
		}

		@Override
		default IllegalArgumentException fatal(int index, String s) {
			YmlMobs.LOGGER.fatal("Fatal error occurs in file %s line %d column %d: ".formatted(file(), line(), index) + s);
			return new IllegalArgumentException(s);
		}

	}

	record SubElem(ParserLogger.File parent, int start) implements Sub {

		@Override
		public int defaultIndex() {
			return start;
		}
	}

}
