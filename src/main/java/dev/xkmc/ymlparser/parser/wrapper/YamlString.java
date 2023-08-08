package dev.xkmc.ymlparser.parser.wrapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

public class YamlString extends YamlElement {

	private final String val;

	public YamlString(String value) {
		this.val = value;
	}

	@Override
	public String toString() {
		return val;
	}

	@Override
	public Node asNode() {
		return new ScalarNode(Tag.STR, val, null, null, DumperOptions.ScalarStyle.PLAIN);
	}

	@Override
	public JsonElement asJson() {
		return new JsonPrimitive(val);
	}

}
