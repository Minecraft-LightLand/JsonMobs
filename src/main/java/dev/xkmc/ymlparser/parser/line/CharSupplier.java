package dev.xkmc.ymlparser.parser.line;

public class CharSupplier {

	protected final String str;
	protected int index = 0;

	public CharSupplier(String str) {
		this.str = str;
	}


	public boolean isEmpty() {
		return index >= str.length();
	}

	public char pop() {
		return str.charAt(index++);
	}

	public char peek() {
		return str.charAt(index);
	}

	public int getIndex() {
		return index;
	}

}
