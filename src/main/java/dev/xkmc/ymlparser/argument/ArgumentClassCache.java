package dev.xkmc.ymlparser.argument;

import dev.xkmc.l2serial.serialization.type_cache.ClassCache;
import dev.xkmc.l2serial.util.LazyExc;
import dev.xkmc.l2serial.util.Wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArgumentClassCache<T> {

	private static final Map<Class<?>, ArgumentClassCache<?>> CACHE = new ConcurrentHashMap<>();

	public static <T> ArgumentClassCache<T> get(Class<T> cls) {
		if (CACHE.containsKey(cls)) {
			return Wrappers.cast(CACHE.get(cls));
		}
		return new ArgumentClassCache<>(cls);
	}

	private final Class<T> cls;
	private final LazyExc<List<ArgumentField>> fields;
	private final LazyExc<Integer> required;

	private T obj;

	private ArgumentClassCache(Class<T> cls) {
		CACHE.put(cls, this);
		this.cls = cls;
		fields = new LazyExc<>(() -> {
			Class<?> itr = cls;
			List<ArgumentField> ans = new ArrayList<>();
			while (itr != null) {
				ans.addAll(ArgumentClass.get(itr).getArguments());
				itr = itr.getSuperclass();
			}
			return ans;
		});
		required = new LazyExc<>(() -> {
			int ans = 0;
			for (var e : getArguments()) {
				if (!e.arg().optional()) {
					ans++;
				}
			}
			return ans;
		});
	}

	public List<ArgumentField> getArguments() throws Exception {
		return fields.get();
	}

	int getRequired() throws Exception {
		return required.get();
	}

	T create() throws Exception {
		if (getArguments().size() == 0) {
			if (obj == null) {
				obj = Wrappers.cast(ClassCache.get(cls).create());
			}
			return obj;
		}
		return Wrappers.cast(ClassCache.get(cls).create());
	}

	private static class ArgumentClass {

		private static final Map<Class<?>, ArgumentClass> CACHE = new ConcurrentHashMap<>();

		private static ArgumentClass get(Class<?> cls) {
			if (CACHE.containsKey(cls)) {
				return CACHE.get(cls);
			}
			return new ArgumentClass(cls);
		}

		private final ClassCache cache;
		private final LazyExc<List<ArgumentField>> fields;

		ArgumentClass(Class<?> cls) {
			CACHE.put(cls, this);
			this.cache = ClassCache.get(cls);
			this.fields = new LazyExc<>(() -> {
				List<ArgumentField> ans = new ArrayList<>();
				for (var field : cache.getFields()) {
					Argument a = field.getAnnotation(Argument.class);
					if (a != null) {
						ans.add(new ArgumentField(cache, a, field));
					}
				}
				return ans;
			});
		}

		List<ArgumentField> getArguments() throws Exception {
			return fields.get();
		}
	}


}
