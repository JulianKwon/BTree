package bTree;

public class NodeCreate
{
	public static Node root;

	public class Node
	{
		private int size; //key�� ����
		private int childsize; //childtree�� ����
		private int[] key = new int[4];
		private Node[] childtree = new Node[5];
		private Node parent;

		public Node() //������
		{ 
			size = 0;
			childsize = 0;
		}
		
		public boolean isfull()
		{
			if (size == 4)
				return true;
			return false;
		}

		public int getchildsize()
		{
			return childsize;
		}
		
		public int getkey(int index)
		{
			return key[index];
		}

		public Node getchild(int index)
		{
			return childtree[index];
		}

		public int getsize()
		{
			return size;
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
			{
				key[0] = input;
				size++;
			}
			else
			{
				while (input > key[i] && i < size)
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

			for (int i = index; i <= size - 2; i++) // move forward
			{
				key[i] = key[i + 1];
			}
			key[size - 1] = 0;
			size--;
			return removed;
		}

		public void putchild(Node n)
		{
			int i = 0;
			while (n.getkey(0) < key[i])
			{
				i++;
			}
			childtree[i] = n;
			childsize++;
		}

		public Node deletechild(int index)
		{
			Node child = childtree[index];
			for(int i = 0; i < 4 - index; i++)
			{
				childtree[i + index + 1] = childtree[i + index];
			}
			childtree[4] = null;
			childsize--;
			return child;
		}
	}

}
