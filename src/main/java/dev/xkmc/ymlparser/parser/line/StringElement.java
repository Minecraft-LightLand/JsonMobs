package dev.xkmc.ymlparser.parser.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class StringElement {

	public interface Visitor {

		default void enter(StringElement elem) {
		}

		default void exit(StringElement elem) {
		}

		default void append(String str) {
		}

		default void append(char ch) {
		}

	}

	public record StringVisitor(StringBuilder builder) implements Visitor {

		@Override
		public void append(String str) {
			builder.append(str);
		}

		@Override
		public void append(char ch) {
			if (ch != '\0') {
				builder.append(ch);
			}
		}
	}

	public static class ListElem extends StringElement {

		public final List<StringElement> list = new ArrayList<>();

		public ListElem(int start) {
			super(start);
		}

		private void append(int index, char ch) {
			StrElem elem;
			if (list.isEmpty() || !(list.get(list.size() - 1) instanceof StrElem str)) {
				list.add(elem = new StrElem(index));
			} else {
				elem = str;
			}
			elem.append(ch);
		}

		private void appendEscape(int index, char val, char next) {
			list.add(new Escape(index, val, next));
		}

		private void add(Hierarchy elem) {
			list.add(elem);
		}

		@Override
		public void build(Visitor builder) {
			builder.enter(this);
			for (var e : list) {
				e.build(builder);
			}
			builder.exit(this);
		}

		public ListElem subElem(int i) {
			ListElem ans = new ListElem(start + i);
			ans.list.addAll(list);
			String first = list.get(0).toString();
			if (first.length() <= i) {
				ans.list.remove(0);
			} else {
				StrElem str = new StrElem(start + i);
				str.builder.append(first, i, first.length());
				ans.list.set(0, str);
			}
			return ans;
		}
	}

	public static class StrElem extends StringElement {

		private final StringBuilder builder = new StringBuilder();

		private String str = null;

		public StrElem(int start) {
			super(start);
		}

		private void append(char ch) {
			builder.append(ch);
		}

		@Override
		public String toString() {
			if (str != null) return str;
			str = builder.toString();
			return str;
		}

		@Override
		public void build(Visitor builder) {
			builder.enter(this);
			builder.append(this.toString());
			builder.exit(this);
		}

	}

	public static class Escape extends StringElement {

		private final int val;
		private final char text;

		public Escape(int start, int val, char text) {
			super(start);
			this.val = val;
			this.text = text;
		}

		@Override
		public void build(Visitor builder) {
			builder.enter(this);
			builder.append('\\');
			builder.append(text);
			builder.exit(this);
		}

		@Override
		public String toString() {
			return "" + val;
		}

	}

	public static class Hierarchy extends StringElement {

		public final StringHierarchy hierarchy;
		public final List<ListElem> list = new ArrayList<>();

		private ListElem focus = null;

		private Hierarchy(int start, StringHierarchy hierarchy) {
			super(start);
			this.hierarchy = hierarchy;
		}

		@Override
		public void build(Visitor builder) {
			builder.enter(this);
			builder.append(hierarchy.left);
			ListElem prev = null;
			for (ListElem elem : list) {
				if (prev != null) {
					builder.append(hierarchy.split);
				}
				elem.build(builder);
				prev = elem;
			}
			builder.append(hierarchy.right);
			builder.exit(this);
		}

		private ListElem getList(int index) {
			if (focus == null) {
				list.add(focus = new ListElem(index));
			}
			return focus;
		}

		private void split() {
			focus = null;
		}

	}

	static class Builder {

		private final Hierarchy root = new Hierarchy(0, StringHierarchy.NONE);
		private final Stack<Hierarchy> stack = new Stack<>();

		Builder() {
			stack.push(root);
		}

		public void append(int index, char val) {
			stack.peek().getList(index).append(index, val);
		}

		public void end() {
			stack.pop();
		}

		public void start(int start, StringHierarchy h) {
			Hierarchy elem = new Hierarchy(start, h);
			stack.peek().getList(start).add(elem);
			stack.push(elem);
		}

		public void split() {
			stack.peek().split();
		}

		public void appendEscape(int index, char val, char next) {
			stack.peek().getList(index).appendEscape(index, val, next);
		}
	}

	public static StringElement build(String str) {
		return new CharSupplier(str).parseString().root;
	}

	public final int start;

	private String str = null;

	protected StringElement(int start) {
		this.start = start;
	}

	public abstract void build(Visitor builder);

	public String toString() {
		if (str != null) return str;
		StringBuilder builder = new StringBuilder();
		build(new StringVisitor(builder));
		str = builder.toString();
		return str;
	}

}