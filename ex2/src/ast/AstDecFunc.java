package ast;

public class AstDecFunc extends AstDec
{
	public AstType returnType;
	public String name;
	public AstParamList params;
	public AstStmtList body;

	public AstDecFunc(AstType returnType, String name, AstParamList params, AstStmtList body)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== funcDec -> type ID( %s )\n", name);
		this.returnType = returnType;
		this.name = name;
		this.params = params;
		this.body = body;
	}

	public void printMe()
	{
		System.out.format("AST NODE FUNC DEC( %s )\n", name);

		if (returnType != null) returnType.printMe();
		if (params != null) params.printMe();
		if (body != null) body.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("FUNC DEC\n(%s)", name));

		if (returnType != null) AstGraphviz.getInstance().logEdge(serialNumber, returnType.serialNumber);
		if (params != null) AstGraphviz.getInstance().logEdge(serialNumber, params.serialNumber);
		if (body != null) AstGraphviz.getInstance().logEdge(serialNumber, body.serialNumber);
	}
}