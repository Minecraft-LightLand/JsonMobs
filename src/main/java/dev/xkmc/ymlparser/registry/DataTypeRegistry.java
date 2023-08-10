package dev.xkmc.ymlparser.registry;

import com.google.gson.internal.LinkedTreeMap;
import dev.xkmc.ymlmobs.init.YmlMobs;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unused")
public class DataTypeRegistry<T, E extends IDataRegistryEntry<T>> implements IDataTypeRegistry<T> {

	private final Map<String, E> map = new LinkedTreeMap<>();
	private final Map<T, String> reverseMap = new HashMap<>();

	public void register(E entry) {
		putAndCheck(entry.id(), entry);
		reverseMap.put(entry.val(), entry.id());
		for (String id : entry.alias()) {
			putAndCheck(id, entry);
		}
	}

	private void putAndCheck(String id, E entry) {
		if (map.containsKey(id)) {
			var prev = map.get(id);
			boolean currentMain = id.equals(entry.id());
			boolean prevMain = id.equals(prev.id());
			if (currentMain) {
				if (prevMain) {
					YmlMobs.LOGGER.fatal("Conflicting main keys: " + id + ", aborting");
					throw new IllegalArgumentException("Conflicting main keys: " + id);
				} else {
					YmlMobs.LOGGER.error("Conflicting keys: " + id + ", overwriting alias of " + prev.id());
				}
			} else {
				YmlMobs.LOGGER.error("Conflicting keys: " + id + ", skipping alias of " + entry.id());
				return;
			}
		}
		map.put(id, entry);
	}

	@Override
	public Collection<String> getAllKeys() {
		return reverseMap.values();
	}

	@Nullable
	public E get(String str) {
		return map.get(str.toLowerCase(Locale.ROOT));
	}

	public String getID(T val) {
		return reverseMap.get(val);
	}

	public void clear() {
		map.clear();
		reverseMap.clear();
	}

}
