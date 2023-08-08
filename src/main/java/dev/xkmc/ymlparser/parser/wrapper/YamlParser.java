package dev.xkmc.ymlparser.parser.wrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.emitter.Emitter;
import org.yaml.snakeyaml.resolver.Resolver;
import org.yaml.snakeyaml.serializer.Serializer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class YamlParser {

	public static final Yaml YAML = new Yaml();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().disableHtmlEscaping().create();

	public static YamlElement readYaml(InputStream stream) {
		return YamlElement.of(YAML.compose(new InputStreamReader(stream, StandardCharsets.UTF_8)));
	}

	public static void writeYaml(YamlElement elem, OutputStream stream) {
		DumperOptions opt = new DumperOptions();
		opt.setTags(new HashMap<>());
		Emitter emi = new Emitter(new OutputStreamWriter(stream), opt);
		Serializer serializer = new Serializer(emi, new Resolver(), opt, null);
		try {
			serializer.open();
			serializer.serialize(elem.asNode());
			serializer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
