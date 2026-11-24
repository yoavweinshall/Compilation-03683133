package ast;

public class AstType extends AstNode
{
	public String typeName;

	public AstType(String typeName)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== type -> %s\n", typeName);
		this.typeName = typeName;
	}

	public void printMe()
	{
		System.out.format("AST NODE TYPE( %s )\n", typeName);

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("TYPE\n(%s)", typeName));
	}
}