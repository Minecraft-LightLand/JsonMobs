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
		if (h.bundling() != null) {
			stack.push(h.bundling());
			ans.fakeStart(index, h);
		}
	}

	private void end() {
		var e = stack.pop();
		ans.end();
		if (e.isImplicit() && stack.size() > 0) {
			end();
		}
	}

	private boolean transformState(char ch) {
		if (ch == '<') {
			if (isEmpty()) return false;
			char next = peek();
			if (!('a' <= next && next <= 'z' || 'A' <= next && next <= 'Z' || next == '_')) {
				return false;
			}
		}
		for (char sp : stack.peek().split) {
			if (ch == sp) {
				ans.split();
				return true;
			}
		}
		if (stack.peek().isImplicit() && stack.size() > 1) {
			var prev = stack.elementAt(stack.size() - 2);
			for (char sp : prev.split) {
				if (ch == sp) {
					ans.end();
					ans.split();
					ans.start(index, stack.peek());
					return true;
				}
			}
		}
		if (stack.peek().right == ch) {
			end();
			return true;
		}
		if (stack.peek().type == StringHierarchy.Type.VAR) {
			return false;
		}
		if (stack.peek().isImplicit() && stack.size() > 1) {
			var prev = stack.elementAt(stack.size() - 2);
			if (prev.right == ch) {
				end();
				return true;
			}
		}
		for (StringHierarchy t : stack.peek().allowedSubs()) {
			if (t.left != '\0' && ch == t.left) {
				start(t);
				return true;
			}
		}
		return false;
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
