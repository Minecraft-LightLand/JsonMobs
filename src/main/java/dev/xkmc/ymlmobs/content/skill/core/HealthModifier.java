package dev.xkmc.ymlmobs.content.skill.core;

import dev.xkmc.ymlmobs.content.variables.builtin.CasterContext;

import java.util.function.Predicate;

public class HealthModifier {

	public static final HealthModifier NULL = new HealthModifier(e -> true);

	private final Predicate<CasterContext> pred;
	private final boolean once;

	public HealthModifier(Predicate<CasterContext> pred) {
		this(pred, false);
	}

	public HealthModifier(Predicate<CasterContext> pred, boolean once) {
		this.pred = pred;
		this.once = once;
	}

}
