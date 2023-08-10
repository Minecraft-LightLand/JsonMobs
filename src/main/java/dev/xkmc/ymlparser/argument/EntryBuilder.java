package dev.xkmc.ymlparser.argument;

import com.mojang.datafixers.util.Pair;

import java.util.*;

public class EntryBuilder<T> {

	public static <T> T create(Class<T> cls) {
		try {
			return ArgumentClassCache.get(cls).create();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T build(Class<T> cls, IArgumentProvider arguments) {
		try {
			ArgumentClassCache<T> cache = ArgumentClassCache.get(cls);
			T ans = cache.create();
			new ArgumentFiller(cache.getArguments()).fill(ans, arguments);
			return ans;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> int countRequired(Class<T> cls) {
		try {
			ArgumentClassCache<T> cache = ArgumentClassCache.get(cls);
			return cache.getRequired();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	static class ArgumentFiller {

		private final Set<ArgumentField> remain;
		private final Map<String, ArgumentField> map = new HashMap<>();

		private final List<Pair<ArgumentField, IArgumentProvider.Entry>> list = new ArrayList<>();

		ArgumentFiller(List<ArgumentField> list) {
			remain = new HashSet<>(list);
			for (var e : list) {
				for (var str : e.arg().aliases()) {
					map.put(str, e);
				}
			}
			// prevent conflict, which should have been checked
			for (var e : list) {
				map.put(e.arg().name(), e);
			}
		}

		<T> void fill(T obj, IArgumentProvider pvd) throws Exception {
			pvd.handleNamed(this::fillEntry);
			for (var e : remain) {
				if (!e.arg().optional()) {
					fillEntry(e.arg().name(), pvd.pollUnnamed(e.arg().name()));
				}
			}
			for (var pair : list) {
				ArgumentField field = pair.getFirst();
				var entry = pair.getSecond();
				field.field().set(obj, entry.parseValue(field.dataType()));
			}
		}

		void fillEntry(String str, IArgumentProvider.Entry entry) {
			if (!map.containsKey(str)) {
				entry.error("Invalid Key " + str);
				return;
			}
			ArgumentField field = map.get(str);
			if (!remain.contains(field)) {
				entry.error("Repeated Argument" + str);
			}
			remain.remove(field);
			list.add(Pair.of(field, entry));
		}

	}

}
