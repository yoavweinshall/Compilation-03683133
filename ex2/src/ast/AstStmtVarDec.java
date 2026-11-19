package ast;

public class AstStmtVarDec extends AstStmt
{
	public AstDecVar varDec;

	public AstStmtVarDec(AstDecVar varDec)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.print("====================== stmt -> varDec\n");
		this.varDec = varDec;
	}

	public void printMe()
	{
		System.out.print("AST NODE VAR DEC STMT\n");

		if (varDec != null) varDec.printMe();

		AstGraphviz.getInstance().logNode(serialNumber, "VAR DEC\nSTMT");

		if (varDec != null) AstGraphviz.getInstance().logEdge(serialNumber, varDec.serialNumber);
	}
}