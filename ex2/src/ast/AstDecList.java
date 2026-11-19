package ast;

public class AstDecList extends AstNode
{
	public AstDec head;
	public AstDecList tail;

	public AstDecList(AstDec head, AstDecList tail)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		if (tail != null) System.out.print("====================== decList -> dec decList\n");
		if (tail == null) System.out.print("====================== decList -> dec\n");
		this.head = head;
		this.tail = tail;
	}

	public void printMe()
	{
		System.out.print("AST NODE DEC LIST\n");

		if (head != null) head.printMe();
		if (tail != null) tail.printMe();

		AstGraphviz.getInstance().logNode(serialNumber, "DEC\nLIST");

		if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
		if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
	}
}