package dev.xkmc.ymlparser.argument;

import dev.xkmc.l2serial.serialization.type_cache.FieldCache;
import dev.xkmc.ymlparser.type.DataType;

final class ArgumentField {

	private final Argument arg;
	private final FieldCache field;
	private final DataType<?> type;

	ArgumentField(Argument arg, FieldCache field) {
		this.arg = arg;
		this.field = field;
		this.type = DataType.of(field.toType());
	}

	public Argument arg() {
		return arg;
	}

	public FieldCache field() {
		return field;
	}

	public DataType<?> dataType() {
		return type;
	}

}
