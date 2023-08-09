package dev.xkmc.ymlparser.parser.core;

import org.yaml.snakeyaml.Yaml;

public class FileConfigParser {

	private static final Yaml YAML = new Yaml();

	public FileConfigParser() {

	}

	public static FileConfigParser of() {
		return new FileConfigParser();
	}

}
