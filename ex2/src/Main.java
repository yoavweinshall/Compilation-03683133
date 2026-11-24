import java.io.*;
import java.io.PrintWriter;
import ast.*;

public class Main
{
	static public void main(String argv[])
	{
		Lexer l = null;
		Parser p;
		AstNode ast = null;
		FileReader fileReader;
		PrintWriter fileWriter = null;
		String inputFileName = argv[0];
		String outputFileName = argv[1];
		boolean parseSucceeded = false;

		try
		{
			/* [1] Initialize file reader and writer */
			fileReader = new FileReader(inputFileName);
			fileWriter = new PrintWriter(outputFileName);

			/* [2] Initialize lexer and parser */
			l = new Lexer(fileReader);
			p = new Parser(l);

			/* [3] Parse input program */
			ast = (AstNode) p.parse().value;

			/* [4] Write OK and close output file before AST visualization */
			fileWriter.print("OK");
			fileWriter.close();
			fileWriter = null;
			parseSucceeded = true;
		}
		catch (Error e)
		{
			/* Lexical error */
			if (fileWriter != null)
			{
				fileWriter.print("ERROR");
			}
		}
		catch (Exception e)
		{
			/* Syntax error */
			if (fileWriter != null)
			{
				String msg = e.getMessage();
				if (msg != null && msg.matches("\\d+"))
				{
					fileWriter.print("ERROR(" + msg + ")");
				}
				else if (l != null)
				{
					fileWriter.print("ERROR(" + l.getLine() + ")");
				}
				else
				{
					fileWriter.print("ERROR");
				}
			}
		}
		finally
		{
			if (fileWriter != null)
			{
				fileWriter.close();
			}
		}

		/* [5] Print AST and finalize Graphviz DOT file safely */
		if (parseSucceeded && ast != null)
		{
			try
			{
				ast.printMe();
				AstGraphviz.getInstance().finalizeFile();
			}
			catch (Exception ignored)
			{
				/* Do not affect parse output if Graphviz logging fails */
			}
		}
	}
}