package dev.xkmc.ymlparser.parser.wrapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.ArrayList;
import java.util.List;

public class YamlList extends YamlElement {

	public final List<YamlElement> list = new ArrayList<>();

	public void add(YamlElement val) {
		list.add(val);
	}

	@Override
	public Node asNode() {
		List<Node> l = new ArrayList<>();
		for (var e : list) {
			l.add(e.asNode());
		}
		return new SequenceNode(Tag.SEQ, l, DumperOptions.FlowStyle.AUTO);
	}

	@Override
	public JsonElement asJson() {
		JsonArray arr = new JsonArray();
		for (var e : list) {
			arr.add(e.asJson());
		}
		return arr;
	}
}
