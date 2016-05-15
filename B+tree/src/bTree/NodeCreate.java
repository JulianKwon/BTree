package bTree;

public class NodeCreate
{
	public Node root;

	public class Node
	{
		private int size; //key의 개수
		private int childsize; //childtree의 개수
		private int[] key = new int[4];
		private Node[] childtree = new Node[5];
		private Node parent;

		public Node() //생성자
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

		public void deletechild(int index)
		{
			for(int i = 0; i < 4 - index; i++)
			{
				childtree[i + index + 1] = childtree[i + index];
			}
			childtree[4] = null;
			childsize--;
		}
	}

}
