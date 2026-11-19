package ast;

public class AstDecClass extends AstDec
{
	public String name;
	public String parentName;
	public AstDecList fields;

	public AstDecClass(String name, String parentName, AstDecList fields)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		System.out.format("====================== classDec -> CLASS ID( %s )\n", name);
		this.name = name;
		this.parentName = parentName;
		this.fields = fields;
	}

	public void printMe()
	{
		System.out.format("AST NODE CLASS DEC( %s )\n", name);

		if (fields != null) fields.printMe();

		String label = (parentName == null)
			? String.format("CLASS\n(%s)", name)
			: String.format("CLASS\n(%s extends %s)", name, parentName);

		AstGraphviz.getInstance().logNode(serialNumber, label);

		if (fields != null) AstGraphviz.getInstance().logEdge(serialNumber, fields.serialNumber);
	}
}