package ast;

public class AstProgram extends AstNode
{
	public AstDecList decList;

	public AstProgram(AstDecList decList)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.print("====================== program -> decList\n");
		this.decList = decList;
	}

	public void printMe()
	{
		System.out.print("AST NODE PROGRAM\n");

		if (decList != null) decList.printMe();

		AstGraphviz.getInstance().logNode(serialNumber, "PROGRAM");

		if (decList != null) AstGraphviz.getInstance().logEdge(serialNumber, decList.serialNumber);
	}
}