package ast;

public class AstStmtCall extends AstStmt
{
	public AstExpCall call;

	public AstStmtCall(AstExpCall call)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.print("====================== stmt -> callExp SEMICOLON\n");
		this.call = call;
	}

	public void printMe()
	{
		System.out.print("AST NODE CALL STMT\n");

		if (call != null) call.printMe();

		AstGraphviz.getInstance().logNode(serialNumber, "CALL\nSTMT");

		if (call != null) AstGraphviz.getInstance().logEdge(serialNumber, call.serialNumber);
	}
}