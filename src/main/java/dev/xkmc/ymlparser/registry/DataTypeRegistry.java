package dev.xkmc.ymlparser.registry;

import com.google.gson.internal.LinkedTreeMap;
import dev.xkmc.ymlmobs.init.YmlMobs;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class DataTypeRegistry<T> implements IDataTypeRegistry<T> {

	private final Map<String, DataRegistryEntry<T>> map = new LinkedTreeMap<>();
	private final Map<T, String> reverseMap = new HashMap<>();

	public void register(DataRegistryEntry<T> entry) {
		putAndCheck(entry.id(), entry);
		reverseMap.put(entry.val(), entry.id());
		for (String id : entry.alias()) {
			putAndCheck(id, entry);
		}
	}

	private void putAndCheck(String id, DataRegistryEntry<T> entry) {
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

	@Nullable
	public DataRegistryEntry<T> get(String str) {
		return map.get(str);
	}

	public String getID(T val) {
		return reverseMap.get(val);
	}

	public void clear() {
		map.clear();
		reverseMap.clear();
	}

}
