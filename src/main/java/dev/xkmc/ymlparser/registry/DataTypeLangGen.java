package dev.xkmc.ymlparser.registry;

import dev.xkmc.ymlparser.argument.ArgumentField;

public interface DataTypeLangGen {

	void addLang(String descID, String desc);

	void addArg(ArgumentField field);
}
