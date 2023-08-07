package dev.xkmc.ymlparser.parser.line;

public enum StringHierarchy {
	DQ(Type.STRING, '"', '"', '\0'),
	SQ(Type.STRING, '\'', '\'', '\0'),
	C(Type.DATA, '{', '}', ';'),
	B(Type.DATA, '[', ']', ','),
	A(Type.VAR, '<', '>', '.'),
	NONE(Type.NONE, '\0', '\0', ' ');

	final Type type;
	final char left, right, split;

	StringHierarchy(Type type, char left, char right, char split) {
		this.type = type;
		this.left = left;
		this.right = right;
		this.split = split;
	}

	enum Type {
		NONE, STRING, DATA, VAR
	}
}
