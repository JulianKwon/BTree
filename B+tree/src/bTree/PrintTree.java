package bTree;

public class PrintTree extends NodeCreate
{
	public void print(int level, Node rot)
	{
		int size = rot.getsize();
		
		for(int j = 0; j < level - 1; j++)
		{
			System.out.print("   ");
		}
		System.out.print("[" + level + "] ");
		for(int j = 0; j < size; j++)
		{
			if(rot.getkey(j) != 0)
				System.out.print(rot.getkey(j) + " ");
				
		}
		System.out.println();
		if(!rot.isleaf())
		{
			for (int j = 0; j < size + 1; j++)
			{
				print(level + 1, rot.getchild(j));
			}
		}
	}
}