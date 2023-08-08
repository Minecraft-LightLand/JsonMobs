package dev.xkmc.ymlparser.parser.wrapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class YamlMap extends YamlElement {

	public final Map<String, YamlElement> map = new LinkedHashMap<>();

	public void add(String key, YamlElement val) {
		map.put(key, val);
	}

	public YamlElement get(String key) {
		return map.get(key);
	}

	public YamlMap getMap(String key) {
		return get(key).asMap();
	}

	public YamlList getList(String key) {
		return get(key).asList();
	}

	public YamlString getStr(String key) {
		return get(key).asString();
	}

	@Override
	public Node asNode() {
		List<NodeTuple> l = new ArrayList<>();
		for (var e : map.entrySet()) {
			l.add(new NodeTuple(new YamlString(e.getKey()).asNode(), e.getValue().asNode()));
		}
		return new MappingNode(Tag.MAP, l, DumperOptions.FlowStyle.AUTO);
	}

	@Override
	public JsonElement asJson() {
		JsonObject obj = new JsonObject();
		for (var e : map.entrySet()) {
			obj.add(e.getKey(), e.getValue().asJson());
		}
		return obj;
	}
}
