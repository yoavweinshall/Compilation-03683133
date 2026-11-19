package ast;

public class AstDecArray extends AstDec
{
	public String name;
	public AstType elemType;

	public AstDecArray(String name, AstType elemType)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== arrayTypedef -> ARRAY ID( %s )\n", name);
		this.name = name;
		this.elemType = elemType;
	}

	public void printMe()
	{
		System.out.format("AST NODE ARRAY TYPEDEF( %s )\n", name);

		if (elemType != null) elemType.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("ARRAY\n(%s)", name));

		if (elemType != null) AstGraphviz.getInstance().logEdge(serialNumber, elemType.serialNumber);
	}
}