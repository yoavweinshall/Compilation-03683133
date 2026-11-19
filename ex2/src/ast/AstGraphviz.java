package ast;

import java.io.File;
import java.io.PrintWriter;

public class AstGraphviz
{
	private PrintWriter fileWriter;
	private static AstGraphviz instance = null;

	private AstGraphviz() {}

	public static AstGraphviz getInstance()
	{
		if (instance == null)
		{
			instance = new AstGraphviz();

			try
			{
				String dirname = "./output/";
				String filename = "AST_IN_GRAPHVIZ_DOT_FORMAT.txt";
				new File(dirname).mkdirs();
				instance.fileWriter = new PrintWriter(dirname + filename);
			}
			catch (Exception e)
			{
				instance.fileWriter = null;
			}

			if (instance.fileWriter != null)
			{
				instance.fileWriter.print("digraph\n");
				instance.fileWriter.print("{\n");
				instance.fileWriter.print("graph [ordering = \"out\"]\n");
			}
		}
		return instance;
	}

	public void logNode(int nodeSerialNumber, String nodeName)
	{
		if (fileWriter != null)
		{
			fileWriter.format(
				"v%d [label = \"%s\"];\n",
				nodeSerialNumber,
				nodeName);
		}
	}

	public void logEdge(
		int fatherNodeSerialNumber,
		int sonNodeSerialNumber)
	{
		if (fileWriter != null)
		{
			fileWriter.format(
				"v%d -> v%d;\n",
				fatherNodeSerialNumber,
				sonNodeSerialNumber);
		}
	}

	public void finalizeFile()
	{
		if (fileWriter != null)
		{
			fileWriter.print("}\n");
			fileWriter.close();
		}
	}
}