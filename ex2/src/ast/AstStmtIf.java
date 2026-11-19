package ast;

public class AstStmtIf extends AstStmt
{
	public AstExp cond;
	public AstStmtList body;
	public AstStmtList elseBody;

	public AstStmtIf(AstExp cond, AstStmtList body, AstStmtList elseBody)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		if (elseBody == null)
		{
			System.out.print("====================== stmt -> IF ( exp ) { stmtList }\n");
		}
		else
		{
			System.out.print("====================== stmt -> IF ( exp ) { stmtList } ELSE { stmtList }\n");
		}
		this.cond = cond;
		this.body = body;
		this.elseBody = elseBody;
	}

	public void printMe()
	{
		System.out.print("AST NODE IF STMT\n");

		if (cond != null) cond.printMe();
		if (body != null) body.printMe();
		if (elseBody != null) elseBody.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			elseBody == null ? "IF" : "IF\nELSE");

		if (cond != null) AstGraphviz.getInstance().logEdge(serialNumber, cond.serialNumber);
		if (body != null) AstGraphviz.getInstance().logEdge(serialNumber, body.serialNumber);
		if (elseBody != null) AstGraphviz.getInstance().logEdge(serialNumber, elseBody.serialNumber);
	}
}