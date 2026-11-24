package ast;

public class AstExpBinop extends AstExp
{
	public int op;
	public AstExp left;
	public AstExp right;

	public AstExpBinop(AstExp left, AstExp right, int op)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.print("====================== exp -> exp BINOP exp\n");
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public void printMe()
	{
		String sop = "";
		if (op == 0) { sop = "+"; }
		if (op == 1) { sop = "-"; }
		if (op == 2) { sop = "*"; }
		if (op == 3) { sop = "/"; }
		if (op == 4) { sop = "<"; }
		if (op == 5) { sop = ">"; }
		if (op == 6) { sop = "="; }
		if (op == 7) { sop = "and"; }
		if (op == 8) { sop = "or"; }

		System.out.print("AST NODE BINOP EXP\n");

		if (left != null) left.printMe();
		if (right != null) right.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("BINOP(%s)", sop));

		if (left != null) AstGraphviz.getInstance().logEdge(serialNumber, left.serialNumber);
		if (right != null) AstGraphviz.getInstance().logEdge(serialNumber, right.serialNumber);
	}
}