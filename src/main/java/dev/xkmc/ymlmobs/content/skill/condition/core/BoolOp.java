package dev.xkmc.ymlmobs.content.skill.condition.core;

enum BoolOp {
	NOT, EQ, XOR, AND, OR;

	boolean isList() {
		return this == AND || this == OR;
	}
}
