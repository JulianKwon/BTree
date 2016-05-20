package bTree;

public class Search extends NodeCreate
{
	public static boolean searchkey(int level, int key, Node root, boolean print)
	{
		int i = 0;
		int size = root.getsize();

		if (print)
		{
			for (int j = 0; j < level - 1; j++)
				System.out.print("   ");
			System.out.print("[" + level + "] ");
		}

		while (i < size && root.getkey(i) < key && root.getkey(i) != key)
			i++;
		
		// find if there exist a key
		if (root.getkey(i) == key)
		{
			if (print)
			{
				for(int j = 0; j < i; j++)
					System.out.print(root.getkey(j) + " ");
				System.out.print("*" + root.getkey(i));
				for(int j = i + 1; j < size; j++)
					System.out.print(root.getkey(j) + " ");
			}
			return true;
		} 
		// no key in this node
		else 
		{
			if(print)
			{
				for(int j = 0; j < size; j++)
					System.out.print(root.getkey(j) + " ");				
			}
			
			//if the node is a leaf there is no existing key in this tree
			if (root.isleaf())
			{
				if (print)
					System.out.println("찾는 키값이 존재하지 않습니다.");
				
				return false;
			} 
			//recursively search for 'key'
			else
			{
				if(print)
					System.out.println();
				return searchkey(level + 1, key, root.getchild(i), print);
			}
		}
	}
}
