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
		Node y = x.getchild(i);
		Node z = x.getchild(i + 1);
		
		if (!x.isleaf())
		{

			if (y.getsize() > 2)
			{
				// find predecessor key first
				int k_ = findpredecessor(x, key);

				// move predecessor key from y to x
				// then move key from x to successor node
				movekey(k_, y, x);
				movekey(key, x, z);

				// recursively move key to the bottom then delete
				deletekey(key, z);
			} else if (z.getsize() > 2)
			{
				int k_ = findsuccessor(x, key);

				movekey(k_, z, x);
				movekey(key, x, y);

				deletekey(key, y);
			} else
			{
				movekey(key, x, y);
				mergenode(y, z);
				deletekey(key, y);
			}
		}
		// x is a leaf and key is in x
		else
		{
			Node w = x.getparent();
			int j = w.getsize() - 1;
			while (j >= 0 && key < w.getkey(j))
				j--;
			j++;
			int v = w.getkey(j);
			
			if(x.getsize() > 2)
				removekey(key, x);
			else if (y.getsize() > 2)
			{
				int k_ = findpredecessor(w, v);
				movekey(k_, y, w);
				k_ = findsuccessor(w, v);
				movekey(k_, w, x);
				
				deletekey(key, x);
			} else if (w.getsize() > 2)
			{
				int k_ = findsuccessor(w, v);
				movekey(k_, z, w);
				k_ = findpredecessor(w, v);
				
				movekey(k_, w, x);
				
				deletekey(key, x);
			} else
			{
				int k = w.getparent().getsize() - 1;
				while (k >= 0 && v < w.getparent().getkey(k))
					k--;
				k++;
				Node s = w.getparent().getchild(k + 1);
				Node w_ = w.getparent();
				
				if(w_.getsize() == 2)
				{
					mergenode(w_, w);
					mergenode(w, s);
					
					deletekey(key, x);
				} else
				{
					movekey(v, w, x);
					deletekey(key, x);
				}
			}
		}
	}

	public static void movekey(int key, Node n1, Node n2)
	{
		int i = n1.getsize() - 1;

		while (i >= 0 && key < n1.getkey(i))
			i--;
		i++;
		n2.addkey(n1.deletekey(i));
	}

	public static void mergenode(Node n1, Node n2)
	{
		int n2size = n2.getsize();

		for (int i = 0; i < n2size; i++)
			n1.addkey(n2.deletekey(0));
		for (int i = 0; i <= n2size; i++)
			n1.putchild(n2.deletechild(0));
	}

	public static void removekey(int k, Node n)
	{
		int i = n.getsize() - 1;
		while (i >= 0 && k < n.getkey(i))
			i--;
		i++;
		n.deletekey(i);
	}

	public static int findpredecessor(Node n, int k)
	{
		int i = n.getsize() - 1;

		while (i >= 0 && k < n.getkey(i))
			i--;
		i++;
		return n.getchild(i).getkey(n.getchild(i).getsize() - 1);
	}

	public static int findsuccessor(Node n, int k)
	{
		int i = n.getsize() - 1;

		while (i >= 0 && k < n.getkey(i))
			i--;
		i++;
		return n.getchild(i + 1).getkey(0);
	}
}
