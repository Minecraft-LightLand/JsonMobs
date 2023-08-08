package dev.xkmc.ymlmobs.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.ymlmobs.init.YmlMobs;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Locale;

public enum LangData {
	;

	final String id, def;
	final int count;

	LangData(String id, String def, int count) {
		this.id = id;
		this.def = def;
		this.count = count;
	}

	public MutableComponent get(Object... objs) {
		if (objs.length != count)
			throw new IllegalArgumentException("for " + name() + ": expect " + count + " parameters, got " + objs.length);
		return translate(YmlMobs.MODID + "." + id, objs);
	}


	public static void addTranslations(RegistrateLangProvider pvd) {
		for (LangData id : LangData.values()) {
			String[] strs = id.id.split("\\.");
			String str = strs[strs.length - 1];
			pvd.add(YmlMobs.MODID + "." + id.id, id.def);
		}
	}

	public static String asId(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
