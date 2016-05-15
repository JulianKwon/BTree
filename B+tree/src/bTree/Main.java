package bTree;

public class Main extends NodeCreate
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MkTree mk = new MkTree();
		PrintTree pt = new PrintTree();
		mk.read("rand100.txt");
		pt.print(1, root);
	}
}
