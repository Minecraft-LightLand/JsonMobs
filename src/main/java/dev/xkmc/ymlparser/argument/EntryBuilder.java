package dev.xkmc.ymlparser.argument;

import com.mojang.datafixers.util.Pair;
import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.ymlparser.compound.CompoundValue;

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
			new EntryBuilder<>().fill(cache.getArguments(), ans, arguments);
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

	private final Set<IArgFieldFiller> remain = new LinkedHashSet<>();
	private final Set<IArgFieldFiller> total = new LinkedHashSet<>();
	private final Map<String, IArgFieldFiller> map = new HashMap<>();

	private final List<Pair<IArgFieldFiller, IArgumentProvider.Entry>> list = new ArrayList<>();

	private final Map<String, IArgumentProvider.Entry> vars = new HashMap<>();//TODO extra parameters

	void fill(List<ArgumentField> list, T obj, IArgumentProvider pvd) throws Exception {
		for (var e : list) {
			IArgFieldFiller iarg = IArgFieldFiller.of(obj, e);
			remain.add(iarg);
			total.add(iarg);
			iarg.fillMap(map);
		}
		pvd.handleNamed(this::fillEntry);
		for (var e : remain) {
			if (!e.arg().optional()) {
				fillEntry(e.arg().name(), pvd.pollUnnamed(e.arg().name()));
			}
		}
		for (var pair : this.list) {
			IArgFieldFiller field = pair.getFirst();
			var entry = pair.getSecond();
			field.fill(entry);
		}
		for (var e : total) {
			e.flush();
		}
	}

	void fillEntry(String str, IArgumentProvider.Entry entry) {
		if (map.containsKey(str)) {
			IArgFieldFiller field = map.get(str);
			if (!remain.contains(field)) {
				remain.remove(field);
			}
			list.add(Pair.of(field, entry));
		}
		vars.put(str, entry);
	}

	interface IArgFieldFiller {

		static <T> IArgFieldFiller of(T obj, ArgumentField e) throws Exception {
			return e.isComplex() ? ArgFieldFiller.of(obj, e) : new Simple<T>(obj, e);
		}

		@SuppressWarnings({"rawtype", "unsafe", "unchecked"})
		static <T> void setField(T obj, ArgumentField field, Object value) throws Exception {
			if (value instanceof Collection coll) {
				var old = field.field().get(obj);
				if (old instanceof Collection oldColl) {
					oldColl.addAll(coll);
					return;
				}
			}
			field.field().set(obj, value);
		}

		Argument arg();

		void fill(IArgumentProvider.Entry entry) throws Exception;

		void fillMap(Map<String, IArgFieldFiller> map) throws Exception;

		void flush() throws Exception;

	}

	record Simple<T>(T obj, ArgumentField field) implements IArgFieldFiller {

		@Override
		public Argument arg() {
			return field.arg();
		}

		@Override
		public void fill(IArgumentProvider.Entry entry) throws Exception {
			IArgFieldFiller.setField(obj, field, entry.parseValue(field.dataType()));
		}

		@Override
		public void fillMap(Map<String, IArgFieldFiller> map) {
			map.put(field.arg().name(), this);
			for (var str : field.arg().aliases()) {
				map.put(str, this);
			}
		}

		@Override
		public void flush() {

		}

	}

	static class ArgFieldFiller<T, R extends CompoundValue<R, T>> implements IArgFieldFiller {

		static <T, R extends CompoundValue<R, T>> ArgFieldFiller<T, R> of(T obj, ArgumentField field) throws Exception {
			Class<R> cls = Wrappers.cast(field.arg().compound());
			return new ArgFieldFiller<>(obj, field, cls);
		}

		final ArgumentField field;
		final ArgumentClassCache<R> cache;
		final T parent;
		final R builder;
		final List<IArgFieldFiller> list = new ArrayList<>();

		boolean direct = false;

		ArgFieldFiller(T parent, ArgumentField field, Class<R> cls) throws Exception {
			this.parent = parent;
			this.field = field;
			cache = ArgumentClassCache.get(cls);
			builder = cache.create();
		}

		public Argument arg() {
			return field.arg();
		}

		@Override
		public void fill(IArgumentProvider.Entry entry) throws Exception {
			direct = true;
			IArgFieldFiller.setField(parent, field, entry.parseValue(field.dataType()));
		}

		@Override
		public void flush() throws Exception {
			if (direct) return;
			for (var e : list) {
				e.flush();
			}
			IArgFieldFiller.setField(parent, field, builder.build());
		}

		@Override
		public void fillMap(Map<String, IArgFieldFiller> map) throws Exception {
			var arguments = ArgumentClassCache.get(field.arg().compound()).getArguments();
			for (var f : arguments) {
				var sub = IArgFieldFiller.of(builder, f);
				sub.fillMap(map);
				list.add(sub);
			}
		}

	}

}
