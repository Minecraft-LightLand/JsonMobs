package dev.xkmc.ymlparser.util;

public class HierarchicalStringParser {

	private static String translateAlternateColorCodes(char c, String s) {
		return s;
	}

	public static String parseMessageSpecialChars(String s) {
		if (s == null) {
			return null;
		} else {
			s = s.replace("<&co>", ":");
			s = s.replace("<&sq>", "'");
			s = s.replace("<&da>", "-");
			s = s.replace("<&bs>", "\\");
			s = s.replace("<&fs>", "/");
			s = s.replace("<&sp>", " ");
			s = s.replace("<&cm>", ",");
			s = s.replace("<&sc>", ";");
			s = s.replace("<&eq>", "=");
			s = s.replace("<&dq>", "\"");
			s = s.replace("<&rb>", "]");
			s = s.replace("<&lb>", "[");
			s = s.replace("<&rc>", "}");
			s = s.replace("<&lc>", "{");
			s = s.replace("<&nl>", "\n");
			s = s.replace("<&nm>", "#");
			s = s.replace("<&skull>", "☠");
			s = s.replace("<&heart>", "❤");
			s = translateAlternateColorCodes('&', s);
			return s;
		}
	}

	public static String unparseMessageSpecialChars(String s) {
		if (s == null) {
			return null;
		} else {
			s = s.replace("-", "<&da>");
			s = s.replace("\\", "<&bs>");
			s = s.replace("/", "<&fs>");
			s = s.replace(" ", "<&sp>");
			s = s.replace(",", "<&cm>");
			s = s.replace(";", "<&sc>");
			s = s.replace("=", "<&eq>");
			s = s.replace("{", "<&lc>");
			s = s.replace("}", "<&rc>");
			s = s.replace("[", "<&lb>");
			s = s.replace("]", "<&rb>");
			s = s.replace("'", "<&sq>");
			return s;
		}
	}

	private static String unparseQuote(String ch, String s) {
		var split = s.split(ch);
		var count = 0;
		var ns = "";
		for (String ss : split) {
			if (count % 2 == 1) {
				ns = ns.concat(ch + unparseMessageSpecialChars(ss) + ch);
			} else {
				ns = ns.concat(ss);
			}
			++count;
		}
		return ns;
	}

	public static String unparseBlock(String s) {
		if (s.contains("\"")) {
			s = unparseQuote("\"", s);
		}

		if (s.contains("'")) {
			s = unparseQuote("'", s);
		}

		int pos = 0;
		int count = 0;
		int ss = 0;
		int sc = 0;
		StringBuilder parsed = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c == '{') {
				if (count == 0) {
					sc = pos;
				}
				++count;
			}
			if (c == '}') {
				--count;
				if (count == 0) {
					String f = s.substring(ss, sc);
					String m = s.substring(sc, pos).replace(" ", "<&csp>").replace("-", "<&da>");
					parsed.append(f).append(m);
					ss = pos;
				}
			}

			++pos;
		}

		parsed.append(s, ss, pos);
		return parsed.toString();
	}

	public static String parseBlock(String s) {
		return s.replace("<&csp>", " ").replace("<&da>", "-").trim();
	}

}

