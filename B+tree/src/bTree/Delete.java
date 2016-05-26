package bTree;

public class Delete extends NodeCreate
{
	static Node root = Main.root;
	
	

//	public static void deletekey(int key, Node x)
//	{
//		// find the index where key might be in
//		int f = x.findkey(key);
//		if (!x.isleaf())
//		{
//			Node y = predecessor(x, key);
//			Node z = successor(x, key, true);
//			if (y.getsize() > 2)
//			{
//				// find predecessor key first
//				int k_ = findpredecessor(x, key);
//
//				// move predecessor key from y to x
//				// then move key from x to successor node
//				movekey(k_, y, x);
//				movekey(key, x, z);
//
//				// recursively move key to the bottom then delete
//				deletekey(key, z);
//			} else if (z.getsize() > 2)
//			{
//				int k_ = findsuccessor(x, key);
//
//				movekey(k_, z, x);
//				movekey(key, x, y);
//
//				deletekey(key, y);
//			} else
//			{
//				movekey(key, x, y);
//				mergenode(y, z);
//				deletekey(key, y);
//			}
//
//		} else
//		{
//			// appropriate subtree(containing key)'s root
//			Node y = x.getparent();
//			
//			// right sibling of x
//			Node z = x.getparent().getchild(x.getparent().findkey(key) + 1);
//			
//			// if key is in x and x is a leaf
//			if (x.getsize() > 2)
//				removekey(key, x);
//			
//			else if (y.getsize() > 2)
//			{
//				int k_ = findpredecessor(y, );
//				movekey(k_, y, w);
//				k_ = findsuccessor(w, v);
//				movekey(k_, w, x);
//
//				deletekey(key, x);
//			} else if (w.getsize() > 2)
//			{
//				int k_ = findsuccessor(w, v);
//				movekey(k_, z, w);
//				k_ = findpredecessor(w, v);
//
//				movekey(k_, w, x);
//
//				deletekey(key, x);
//			} else
//			{
//				int k = w.getparent().getsize() - 1;
//				while (k >= 0 && v < w.getparent().getkey(k))
//					k--;
//				k++;
//				Node s = w.getparent().getchild(k + 1);
//				Node w_ = w.getparent();
//
//				if (w_.getsize() == 2)
//				{
//					mergenode(w_, w);
//					mergenode(w, s);
//
//					deletekey(key, x);
//				} else
//				{
//					movekey(v, w, x);
//					deletekey(key, x);
//				}
//			}
//		}
//	}
	
	public static void deletekey(int key)
	{
		int i = root.getsize();
		while(i >= 0 && key < root.getkey(i))
			i--;
		i++;
		if(root.getkey(i) == key)
		{
			if(root.getchilds() > 0)
			{
				
			}
		}
		
	}

	public static Node predecessor(Node x, int k)
	{
		int i = x.findkey(k);
		if (x.getchild(i).isleaf())
			return x.getchild(i);
		else
			return predecessor(x.getchild(i), k);
	}

	public static Node successor(Node x, int k, boolean leftchild)
	{
		int i = x.findkey(k);
		if (leftchild == true)
		{
			if (x.getchild(i).isleaf())
				return x.getchild(i);
			else
				return successor(x.getchild(i + 1), k, false);
		} else
		{
			if (x.getchild(0).isleaf())
				return x.getchild(0);
			else
				return successor(x.getchild(0), k, false);
		}
	}

	public static void movekey(int key, Node n1, Node n2)
	{
		int i = n1.findkey(key);
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
		int i = n.findkey(k);
		n.deletekey(i);
	}

	public static int findpredecessor(Node n, int k)
	{
		int i = n.findkey(k);
		return n.getchild(i).getkey(n.getchild(i).getsize() - 1);
	}

	public static int findsuccessor(Node n, int k)
	{
		int i = n.findkey(k);
		return n.getchild(i + 1).getkey(0);
	}
}
