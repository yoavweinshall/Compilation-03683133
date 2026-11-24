package ast;

public class AstExpNew extends AstExp
{
	public AstType type;
	public AstExp sizeExp;

	public AstExpNew(AstType type, AstExp sizeExp)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		if (sizeExp == null)
		{
			System.out.print("====================== newExp -> NEW type\n");
		}
		else
		{
			System.out.print("====================== newExp -> NEW type [ exp ]\n");
		}
		this.type = type;
		this.sizeExp = sizeExp;
	}

	public void printMe()
	{
		System.out.print("AST NODE NEW EXP\n");

		if (type != null) type.printMe();
		if (sizeExp != null) sizeExp.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			sizeExp == null ? "NEW" : "NEW\nARRAY");

		if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
		if (sizeExp != null) AstGraphviz.getInstance().logEdge(serialNumber, sizeExp.serialNumber);
	}
}