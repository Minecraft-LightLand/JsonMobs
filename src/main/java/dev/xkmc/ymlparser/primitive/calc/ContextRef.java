package dev.xkmc.ymlparser.primitive.calc;

import dev.xkmc.ymlparser.holder.DataContext;

public class ContextRef {

	private DataContext ctx;

	public DataContext getData() {
		return ctx;
	}

	public void setData(DataContext meta) {
		ctx = meta;
	}

}
