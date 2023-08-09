package dev.xkmc.ymlparser.primitive.core;

import dev.xkmc.ymlparser.holder.DataContext;
import dev.xkmc.ymlparser.holder.DataHolder;

public record StaticData<T>(T val) implements DataHolder<T> {

	@Override
	public T get(DataContext meta) {
		return val;
	}

}
