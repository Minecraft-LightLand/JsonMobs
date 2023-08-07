package organize;

import dev.xkmc.ymlparser.parser.line.StringElement;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Scanner;

public class Test {

	private static class DebugVisitor implements StringElement.Visitor {

		private int level = 0;

		@Override
		public void enter(@NotNull StringElement elem) {
			level++;
			String builder = "  ".repeat(level) + elem.toString() + " - " + elem.getClass().getSimpleName();
			System.out.println(builder);
		}

		@Override
		public void exit(@NotNull StringElement elem) {
			level--;
		}
	}

	public static void main(String[] args) throws Exception {
		File file = new File("./src/test/resources/test.txt");
		String str = new Scanner(file).nextLine();
		System.out.println(str);
		StringElement.build(str).build(new DebugVisitor());
	}

}
