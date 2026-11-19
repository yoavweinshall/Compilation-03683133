package ast;

public class AstParamList extends AstNode
{
	public AstType type;
	public String name;
	public AstParamList tail;

	public AstParamList(AstType type, String name, AstParamList tail)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== param -> type ID( %s )\n", name);
		this.type = type;
		this.name = name;
		this.tail = tail;
	}

	public void printMe()
	{
		System.out.format("AST NODE PARAM( %s )\n", name);

		if (type != null) type.printMe();
		if (tail != null) tail.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("PARAM\n(%s)", name));

		if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
		if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
	}
}