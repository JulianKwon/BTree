package bTree;

public class Search extends NodeCreate
{
	public static boolean searchkey(int key, Node root)
	{
		int i = 0;
		if (root == null)
			return false;
		while (root.getkey(i) >= key && i < root.getsize())
		{
			
		}
		
	}
}
