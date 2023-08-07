package dev.xkmc.ymlparser.parser.line;

import java.util.Stack;

public class CharSupplier {

	private int parseEscape(char ch) {
		return switch (ch) {
			case '\\' -> '\\';
			case '"' -> '"';
			case '\'' -> '\'';
			case 'n' -> '\n';
			case 'r' -> '\r';
			case 't' -> '\t';
			default -> -1;
		};
	}

	private final String str;
	private final Stack<StringHierarchy> stack = new Stack<>();
	private final StringElement.Builder ans = new StringElement.Builder();

	private int index = 0;

	CharSupplier(String str) {
		this.str = str;
		stack.push(StringHierarchy.NONE);
	}

	private boolean isEmpty() {
		return index >= str.length();
	}

	private char pop() {
		return str.charAt(index++);
	}

	private char peek() {
		return str.charAt(index);
	}

	private void start(StringHierarchy h) {
		stack.push(h);
		ans.start(index, h);
	}

	private void end() {
		stack.pop();
		ans.end();
	}

	private boolean transformState(char ch) {
		if (ch == '<') {
			if (isEmpty()) return false;
			char next = peek();
			if (!('a' <= next && next <= 'z' || 'A' <= next && next <= 'Z' || next == '_')) {
				return false;
			}
		}
		if (stack.peek().split != '\0') {
			if (ch == stack.peek().split) {
				ans.split();
				return true;
			}
		}
		if (stack.peek().type == StringHierarchy.Type.VAR) {
			if (stack.peek().right == ch) {
				end();
				return true;
			}
			return false;
		} else if (stack.peek().type == StringHierarchy.Type.STRING) {
			if (ch == StringHierarchy.A.left) {
				start(StringHierarchy.A);
				return true;
			} else if (stack.peek().right == ch) {
				end();
				return true;
			}
			return false;
		} else {
			if (!stack.isEmpty() && stack.peek().right == ch) {
				end();
				return true;
			} else {
				for (StringHierarchy t : StringHierarchy.values()) {
					if (t.left != '\0' && ch == t.left) {
						start(t);
						return true;
					}
				}
			}
			return false;
		}
	}

	public StringElement.Builder parseString() {
		while (!isEmpty()) {
			char ch = pop();
			if (ch == '\\') {
				if (isEmpty()) {
					throw new IllegalStateException("invalid line escape at end of string " + str);
				} else {
					char next = pop();
					int val = parseEscape(next);
					if (val < 0) {
						throw new IllegalStateException("invalid escape char " + next + " at " + index + " of string " + str);
					} else {
						ans.appendEscape(index, (char) val, next);
					}
				}
			} else if (!transformState(ch)) {
				ans.append(index, ch);
			}
		}
		while (!stack.isEmpty()) {
			end();
		}
		return ans;
	}

}
