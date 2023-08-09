package organize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.wrapper.YamlElement;
import dev.xkmc.ymlparser.parser.wrapper.YamlParser;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test {

	private static class DebugVisitor implements StringElement.Visitor {

		private int level = 0;

		@Override
		public void enter(@NotNull StringElement elem) {
			level++;
			String builder = "| ".repeat(level) + elem + " - " + elem.getClass().getSimpleName();
			System.out.println(builder);
		}

		@Override
		public void exit(@NotNull StringElement elem) {
			level--;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("abc{0}def{1}ghi{2}".formatted("A", "B", "C"));
	}

	private static void testParse() throws Exception {

		File file = new File("./src/test/resources/test.yaml");
		YamlElement node = YamlParser.readYaml(new FileInputStream(file));
		String str = node.asMap().getMap("Data").getList("Param").list.get(4).asString().toString();
		System.out.println(str);
		StringElement.build(str).build(new DebugVisitor());
		OutputStream w = new FileOutputStream("./src/test/resources/out.yaml");
		YamlParser.writeYaml(node, w);
		FileWriter fw = new FileWriter("./src/test/resources/out.json", StandardCharsets.UTF_8);
		Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().disableHtmlEscaping().create();
		fw.write(gson.toJson(node.asJson()));
		fw.close();
	}

}
