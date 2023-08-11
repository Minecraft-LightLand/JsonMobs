package dev.xkmc.ymlmobs.content.skill.condition.parse;

enum BoolOp {
	NOT, EQ, XOR, AND, OR;

	boolean isList() {
		return this == AND || this == OR;
	}
}
