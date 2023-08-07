package dev.xkmc.ymlparser.parser;

import io.lumine.mythic.bukkit.utils.config.file.YamlConfiguration;

public class FileConfigParser {

	public FileConfigParser(YamlConfiguration yamlConfiguration) {

	}

	public static FileConfigParser of() {
		return new FileConfigParser(new YamlConfiguration());
	}

}
