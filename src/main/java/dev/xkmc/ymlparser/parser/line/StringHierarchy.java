package dev.xkmc.ymlparser.parser.line;

import javax.annotation.Nullable;
import java.util.List;

public enum StringHierarchy {
	DQ(Type.STRING, '"', '"', ""),
	SQ(Type.STRING, '\'', '\'', ""),
	CURVE(Type.DATA, '{', '}', ";"),
	PAREN(Type.DATA, '(', ')', ""),
	BRACKET(Type.LIST, '[', ']', ",-"),
	ARROW(Type.VAR, '<', '>', "."),
	NONE(Type.NONE, '\0', '\0', " ");

	public final Type type;
	final char left, right;
	final char[] split;

	StringHierarchy(Type type, char left, char right, String split) {
		this.type = type;
		this.left = left;
		this.right = right;
		this.split = split.toCharArray();
	}

	boolean isImplicit() {
		return this == NONE;
	}

	@Nullable
	StringHierarchy bundling() {
		return this == BRACKET ? NONE : null;
	}

	List<StringHierarchy> allowedSubs() {
		return switch (type) {
			case VAR, LIST -> List.of();
			case STRING -> List.of(ARROW);
			case DATA, NONE -> List.of(DQ, SQ, PAREN, CURVE, BRACKET, ARROW);
		};
	}

	public enum Type {
		NONE, STRING, LIST, DATA, VAR
	}
}
