package bTree;

public class NodeCreate
{
	public static class Node {
		private int size; // number of key
		private int childsize; // number of childtree
		private int[] key = new int[4];
		private Node[] childtree = new Node[5];
		private boolean isleaf;

		public Node() // initialize
		{
			size = 0;
			childsize = 0;
			isleaf = true;
		}

		public boolean isfull() {
			if (size == 4)
				return true;
			return false;
		}

		public boolean isleaf() {
			return isleaf;
		}
		
		public void putisleaf(boolean b){
			isleaf = b;
		}

		// find if there exists key
		public boolean nokey(int k) {
			boolean chk = true;
			for (int i = 0; i < size; i++) {
				if (k == key[i]) {
					chk = false;
					break;
				}
			}
			return chk;
		}

		public int getchildsize() {
			return childsize;
		}

		public int getkey(int index) {
			return key[index];
		}

		public Node getchild(int index) {
			return childtree[index];
		}

		public int getsize() {
			return size;
		}

		public void addkey(int input) {
			int i = 0;
			if (size == 0) {
				key[0] = input;
				size++;
			} else {
				while (input > key[i] && i < size)
					i++;
				for (int j = size - 1; j >= i; j--)
					key[j + 1] = key[j];
				key[i] = input;

				size++;
			}
		}

		public int deletekey(int index) {
			int removed = key[index];

			for (int i = index; i <= size - 2; i++) // move forward
				key[i] = key[i + 1];
			key[size - 1] = -1;
			size--;
			return removed;
		}

		public void putchild(Node n) {
			
			if (childsize == 0)
			{
				childtree[0] = n;
				childsize++;
			}
			else {
				int i = size - 1;
				while (n.getkey(0) < key[i] && i >= 0)
					i--;
				i++;
				for (int j = childsize - 1; j >= i; j--) 
					childtree[j+1] = childtree[j];
				
				childtree[i] = n;
				childsize++;
			}
		}

		public Node deletechild(int index) {
			Node child = childtree[index];
			for (int i = index; i < childsize - 1; i++)
				childtree[i] = childtree[i + 1];

			childtree[childsize - 1] = null;
			childsize--;
			return child;
		}
	}
}
