package dev.xkmc.ymlmobs.init.registrate;

import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.ymlparser.argument.ArgumentField;
import dev.xkmc.ymlparser.registry.DataTypeLangGen;

import java.util.HashSet;
import java.util.Set;

public class YMRegistrate extends L2Registrate implements DataTypeLangGen {

	private final Set<ArgumentField> added = new HashSet<>();

	public YMRegistrate(String modid) {
		super(modid);
	}

	@Override
	public void addLang(String descID, String desc) {
		addRawLang(descID, desc);
	}

	@Override
	public void addArg(ArgumentField field) {
		if (added.contains(field)) return;
		addRawLang(field.descID(), field.arg().description());
	}

}
