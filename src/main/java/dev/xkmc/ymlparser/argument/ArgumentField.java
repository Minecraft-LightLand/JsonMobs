package dev.xkmc.ymlparser.argument;

import dev.xkmc.l2serial.serialization.type_cache.ClassCache;
import dev.xkmc.l2serial.serialization.type_cache.FieldCache;
import dev.xkmc.ymlparser.type.DataType;

import java.util.Locale;

public final class ArgumentField {

	private final ClassCache parent;
	private final Argument arg;
	private final FieldCache field;
	private final DataType<?> type;

	ArgumentField(ClassCache cache, Argument arg, FieldCache field) {
		this.parent = cache;
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

	public String descID() {
		return parent.cls.getSimpleName().toLowerCase(Locale.ROOT) + "." + arg.name();
	}

}
