package bTree;

public class NodeCreate
{
	public Node root;

	public class Node
	{
		private int size = 0;
		private int childsize = 0;
		private int[] key = new int[4];
		private Node[] childtree = new Node[5];
		private Node parent;

		public int getchildsize()
		{
			return childsize;
		}
		public void putchildsize(int i)
		{
			childsize = i;
		}
		
		public int getkey(int index){
			return key[index];
		}
		
		public Node getchild(int index){
			return childtree[index];
		}
		
		public int getsize()
		{
			return size;
		}

		public Node(int input)
		{
			addkey(input);
		}

		public Node getparent()
		{
			return parent;
		}

		public void putparent(Node p)
		{
			parent = p;
		}

		public void addkey(int input)
		{
			int i = 0;
			if (size == 0)
				key[0] = input;
			else
			{
				while (input > key[i])
					i++;
				for (int j = size - 1; j >= i; j--)
				{
					key[j + 1] = key[j];
				}
				key[i] = input;

				size++;
			}
		}

		public int deletekey(int index)
		{
			int removed = key[index];

			for (int i = index + 1; i <= size - 1; i++)
			{
				key[i - 1] = key[i];
			}
			size--;
			key[size] = 0;
			return removed;
		}
	}

}
