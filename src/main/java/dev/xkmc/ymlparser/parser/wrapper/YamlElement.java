package dev.xkmc.ymlparser.parser.wrapper;

import com.google.gson.JsonElement;
import dev.xkmc.l2serial.util.Wrappers;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

public abstract class YamlElement {

	public static YamlElement of(Node node) {
		if (node instanceof ScalarNode scalar) {
			return new YamlString(scalar.getValue());
		}
		if (node instanceof MappingNode mapping) {
			YamlMap obj = new YamlMap();
			for (var e : mapping.getValue()) {
				obj.add(of(e.getKeyNode()).asString().toString(), of(e.getValueNode()));
			}
			return obj;
		}
		if (node instanceof SequenceNode seq) {
			YamlList arr = new YamlList();
			for (var e : seq.getValue()) {
				arr.add(of(e));
			}
			return arr;
		}
		throw new IllegalStateException("Unreachable");
	}

	public static YamlElement of(JsonElement elem) {
		if (elem.isJsonObject()) {
			YamlMap ans = new YamlMap();
			for (var e : elem.getAsJsonObject().entrySet()) {
				ans.add(e.getKey(), of(e.getValue()));
			}
			return ans;
		}
		if (elem.isJsonArray()) {
			YamlList ans = new YamlList();
			for (var e : elem.getAsJsonArray()) {
				ans.add(of(e));
			}
			return ans;
		}
		return new YamlString(elem.toString());
	}

	public YamlMap asMap() {
		return Wrappers.cast(this);
	}

	public YamlList asList() {
		return Wrappers.cast(this);
	}

	public YamlString asString() {
		return Wrappers.cast(this);
	}

	public abstract Node asNode();

	public abstract JsonElement asJson();

}
