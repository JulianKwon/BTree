package bTree;

public class Delete extends NodeCreate
{
	Node root = Main.root;
	
	public static void deletekey(int key, Node x)
	{
		int i = x.getsize() - 1;
		while (i >= 0 && key < x.getkey(i))
				i--;
			i++;
		if(!x.isleaf())
		{
		}
	}
}
