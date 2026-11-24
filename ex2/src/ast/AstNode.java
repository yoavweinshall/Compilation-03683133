package ast;

public abstract class AstNode
{
	/* Unique serial number for Graphviz DOT visualization */
	public int serialNumber;

	/* Line number in source code for semantic error reporting */
	public int lineNumber;

	public void printMe()
	{
		System.out.print("AST NODE UNKNOWN\n");
	}
}