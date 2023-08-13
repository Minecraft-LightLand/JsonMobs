package dev.xkmc.ymlparser.compound;

public abstract class CompoundValue<A extends CompoundValue<A, T>, T> {

	public abstract T build();

}
