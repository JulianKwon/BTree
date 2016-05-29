package bTree;

public class NodeCreate
{
	public static class Node
	{
		private int size; // size
		private int childsize;
		private int[] key = new int[5];
		private Node[] childtree = new Node[6];
		private boolean isleaf;
		private Node parent;

		public Node() // initialize
		{
			size = 0;
			parent = null;
			isleaf = true;
		}

		public boolean isfull()
		{
			if (size == 5)
				return true;
			return false;
		}

		public boolean isleaf()
		{
			return isleaf;
		}

		public void putisleaf(boolean b)
		{
			isleaf = b;
		}
		
		public void delparent()
		{
			parent = null;
		}

		public int getkey(int index)
		{
			return key[index];
		}

		public Node getchild(int index)
		{
			return childtree[index];
		}
		
		public int getchilds()
		{
			return childsize;
		}

		public int getsize()
		{
			return size;
		}

		public Node getparent()
		{
			return parent;
		}

		public void addkey(int input)
		{
			int i = 0;
			if (size == 0)
			{
				key[0] = input;
				size++;
			} else
			{
				while (input > key[i] && i < size)
					i++;
				for (int j = size - 1; j >= i; j--)
					key[j + 1] = key[j];
				key[i] = input;

				size++;
			}
		}

		public int deletekey(int index)
		{
			int removed = key[index];

			for (int i = index; i < size - 1; i++) // move forward
				key[i] = key[i + 1];
			key[size - 1] = 0;
			size--;
			return removed;
		}

		public void putchild(Node n)
		{
			n.parent = this;
			if (childsize == 0)
			{
				childtree[0] = n;
				childsize++;
			}
			
			else
			{
				int i = size - 1;
				
				// find if 0th key of node n is smaller than key[i]
				while (n.getkey(0) < key[i] && i > 0)
					i--;
				i++;
				for (int j = childsize - 1; j >= i; j--)
					childtree[j + 1] = childtree[j];
				childtree[i] = n;
				childsize++;
			}
			isleaf = false;
		}

		public Node deletechild(int index)
		{
			Node child = childtree[index];
			for (int i = index; i < childsize - 1; i++)
				childtree[i] = childtree[i + 1];

			childtree[childsize - 1] = null;
			childsize--;
			return child;
		}
	}
}
