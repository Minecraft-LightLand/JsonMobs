package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.parser.core.ParserLogger;
import net.minecraft.ChatFormatting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringPlaceholderHelper {

	private static final Map<String, String> MAP = new ConcurrentHashMap<>();

	static {
		MAP.put("&co", ":");
		MAP.put("&sq", "'");
		MAP.put("&da", "-");
		MAP.put("&bs", "\\");
		MAP.put("&fs", "/");
		MAP.put("&sp", " ");
		MAP.put("&cm", ",");
		MAP.put("&sc", ";");
		MAP.put("&eq", "=");
		MAP.put("&dq", "\"");
		MAP.put("&rb", "]");
		MAP.put("&lb", "[");
		MAP.put("&rc", "}");
		MAP.put("&lc", "{");
		MAP.put("&nl", "\n");
		MAP.put("&nm", "#");
		MAP.put("&skull", "☠");
		MAP.put("&heart", "❤");
		for (var e : ChatFormatting.values()) {
			MAP.put("&" + e.getChar(), "§" + e.getChar());
		}
	}

	public static String parse(ParserLogger logger, String str) {
		if (!MAP.containsKey(str)) {
			logger.error("failed to find placeholder " + str);
			return "";
		}
		return MAP.get(str);
	}

}
