package bTree;

public class Search extends NodeCreate
{
	public static boolean searchkey(int key, Node root)
	{
		int i = 0;
		while (root.getkey(i) > key && i < root.getsize())
			i++;
		if (root.isleaf())
		{
			if(root.getkey(i + 1) == key)
				return true;
			else
				return false;
		}else
			return searchkey(key, root.getchild(i));
	}
	
	public static void searchtrace(int level, int key, Node root)
	{
		int i = 0;
		int size = root.getsize();
		
		for(int j = 0 ; j < level - 1; j++)
			System.out.print("   ");
		System.out.print("[" + level + "] ");
		while (root.get)
	}
}
