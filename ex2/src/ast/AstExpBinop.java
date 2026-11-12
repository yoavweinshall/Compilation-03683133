package ast;

public class AstExpBinop extends AstExp
{
	int op;
	public AstExp left;
	public AstExp right;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstExpBinop(AstExp left, AstExp right, int op)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> exp BINOP exp\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	/*************************************************/
	/* The printing message for a binop exp AST node */
	/*************************************************/
	public void printMe()
	{
		String sop="";
		
		/*********************************/
		/* CONVERT op to a printable sop */
		/*********************************/
		if (op == 0) {sop = "+";}
		if (op == 1) {sop = "-";}
		
		/*************************************/
		/* AST NODE TYPE = AST BINOP EXP */
		/*************************************/
		System.out.print("AST NODE BINOP EXP\n");

		/**************************************/
		/* RECURSIVELY PRINT left + right ... */
		/**************************************/
		if (left != null) left.printMe();
		if (right != null) right.printMe();
		
		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AstGraphviz.getInstance().logNode(
				serialNumber,
			String.format("BINOP(%s)",sop));
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (left  != null) AstGraphviz.getInstance().logEdge(serialNumber,left.serialNumber);
		if (right != null) AstGraphviz.getInstance().logEdge(serialNumber,right.serialNumber);
	}
}
