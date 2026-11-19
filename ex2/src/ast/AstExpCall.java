package ast;

public class AstExpCall extends AstExp
{
	public AstVar var;
	public String funcName;
	public AstExpList args;

	public AstExpCall(AstVar var, String funcName, AstExpList args)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== callExp -> ID( %s ) ( args )\n", funcName);
		this.var = var;
		this.funcName = funcName;
		this.args = args;
	}

	public void printMe()
	{
		System.out.format("AST NODE CALL EXP( %s )\n", funcName);

		if (var != null) var.printMe();
		if (args != null) args.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("CALL\n(%s)", funcName));

		if (var != null) AstGraphviz.getInstance().logEdge(serialNumber, var.serialNumber);
		if (args != null) AstGraphviz.getInstance().logEdge(serialNumber, args.serialNumber);
	}
}