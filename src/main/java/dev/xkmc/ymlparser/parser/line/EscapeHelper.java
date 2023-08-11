package dev.xkmc.ymlparser.parser.line;

public class EscapeHelper {

	public static char parseEscape(char ch, LineCharSupplier sup) {
		return switch (ch) {
			case 'n' -> '\n';
			case 'r' -> '\r';
			case 't' -> '\t';
			case 'u' -> {
				StringBuilder str = new StringBuilder();
				for (int i = 0; i < 4; i++) {
					if (sup.isEmpty()) {
						throw new IllegalArgumentException("illegal escape string " + str);
					}
					str.append(sup.pop());
				}
				try {
					yield (char) (int) Integer.valueOf(str.toString(), 16);
				} catch (Exception e) {
					throw new IllegalArgumentException("illegal escape string " + str);
				}
			}
			default -> ch;
		};
	}

}
