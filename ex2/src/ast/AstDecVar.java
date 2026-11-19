package ast;

public class AstDecVar extends AstDec
{
	public AstType type;
	public String name;
	public AstExp initExp;

	public AstDecVar(AstType type, String name, AstExp initExp)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== varDec -> type ID( %s )\n", name);
		this.type = type;
		this.name = name;
		this.initExp = initExp;
	}

	public void printMe()
	{
		System.out.format("AST NODE VAR DEC( %s )\n", name);

		if (type != null) type.printMe();
		if (initExp != null) initExp.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("VAR DEC\n(%s)", name));

		if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
		if (initExp != null) AstGraphviz.getInstance().logEdge(serialNumber, initExp.serialNumber);
	}
}