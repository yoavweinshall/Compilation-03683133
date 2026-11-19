package ast;

public class AstExpString extends AstExp
{
	public String value;

	public AstExpString(String value)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== exp -> STRING( %s )\n", value);
		this.value = value;
	}

	public void printMe()
	{
		System.out.format("AST NODE STRING( %s )\n", value);

		/* Strip double quotes to keep Graphviz DOT label syntax valid */
		String clean = value.replace("\"", "");
		AstGraphviz.getInstance().logNode(
			serialNumber,
			String.format("STRING(%s)", clean));
	}
}