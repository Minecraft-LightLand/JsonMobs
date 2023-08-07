package dev.xkmc.ymlparser.type;

import dev.xkmc.l2serial.util.Wrappers;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EnumType<T extends Enum<T>> implements IterableType<T> {

	private static final Map<Class<?>, EnumType<?>> CACHE = new ConcurrentHashMap<>();

	public static <T extends Enum<T>> EnumType<T> of(Class<T> type) {
		return Wrappers.cast(CACHE.computeIfAbsent(type, k -> new EnumType<>(Wrappers.cast(k))));
	}

	private final String name;
	private final Map<String, T> map;

	private EnumType(Class<T> cls) {
		this.name = cls.getSimpleName();
		this.map = new LinkedHashMap<>();
		for (T t : cls.getEnumConstants()) {
			map.put(t.name(), t);
		}
	}

	@Override
	public String name() {
		return name;
	}

	@Nullable
	@Override
	public SupplierEntry<T> get(String str) {
		T val = map.get(str);
		if (val == null) return null;
		return new Entry<>(this, val);
	}

	@Override
	public Collection<String> getAllKeys() {
		return map.keySet();
	}

	private record Entry<T extends Enum<T>>(EnumType<T> type, T val) implements SupplierEntry<T> {

		@Override
		public T parse() {
			return val;
		}
	}

}
