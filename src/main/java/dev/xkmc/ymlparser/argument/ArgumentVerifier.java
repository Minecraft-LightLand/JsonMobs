package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlmobs.init.YmlMobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentVerifier {

	public static void verify(String id, List<ArgumentField> field) {
		Map<String, ArgumentField> map = new HashMap<>();
		for (ArgumentField f : field) {
			if (map.containsKey(f.arg().name())) {
				YmlMobs.LOGGER.fatal("Conflicting main keys: " + id + ", aborting");
				throw new IllegalArgumentException("Conflicting main keys: " + id);
			} else {
				map.put(f.arg().name(), f);
			}
		}
		for (ArgumentField f : field) {
			for (String alias : f.arg().aliases()) {
				if (map.containsKey(alias)) {
					ArgumentField other = map.get(alias);
					YmlMobs.LOGGER.fatal("Conflicting aliases: " + f.arg().name() + " and " + other.arg().name() + " both have alias" + id + ", aborting");
					throw new IllegalArgumentException("Conflicting aliases: " + id + " by " + f.arg().name() + " and " + other.arg().name());
				} else {
					map.put(alias, f);
				}
			}
		}
	}

}
