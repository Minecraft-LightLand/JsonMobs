package dev.xkmc.ymlparser.parser.line;

import javax.annotation.Nullable;
import java.util.List;

public enum StringHierarchy {
	DQ(Type.STRING, '"', '"', ""),
	SQ(Type.STRING, '\'', '\'', ""),
	C(Type.DATA, '{', '}', ";"),
	B(Type.LIST, '[', ']', ",-"),
	A(Type.VAR, '<', '>', "."),
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
		return this == B ? NONE : null;
	}

	List<StringHierarchy> allowedSubs() {
		return switch (type) {
			case VAR, LIST -> List.of();
			case STRING -> List.of(A);
			case DATA, NONE -> List.of(DQ, SQ, C, B, A);
		};
	}

	public enum Type {
		NONE, STRING, LIST, DATA, VAR
	}
}
