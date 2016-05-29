package bTree;

public class Delete extends NodeCreate
{
	static Node root = Main.root;
	
	public static void delete(int key)
	{
		delete(root, key);
		
		if(root.getsize() == 0)
		{
			Node tmp = root;
			if(root.isleaf())
				root = null;
			else
			{
				root = root.getchild(0);
				root.delparent();
			}
		}
	}
	
	public static void delete(Node x, int key)
	{
		int i = x.findkey(key);
		int n = x.getsize() - 1;
		
		// if key is in x
		if (i < n && x.getkey(i) == key)
	    {
	 
	        // If the node is a leaf node - removeFromLeaf is called
	        // Otherwise, removeFromNonLeaf function is called
	        if (x.isleaf())
	            x.deletekey(i);
	        else
	            deletefromnonleaf(x, key);
	    }
	    else
	    {
	    	boolean last = ((i == x.findkey(key))? true : false);
	    	
	    	if (x.getchild(i).getsize() < 3)
	    		fill(i);
	    	
	    	if (last && i > n)
	    		delete(x.getchild(i - 1), key);
	    	else
	    		delete(x.getchild(i), key);
	    }
	}
	
	

	public static void deletefromnonleaf(Node x, int key)
	{
		int i = x.findkey(key);

		
		if (x.getchild(i).getsize() >= 3)
	    {
	        int pred = findpredecessor(x, key);
	        x.deletekey(i);
	        x.addkey(pred);
	        delete(x.getchild(i), pred);
	    } else if (x.getchild(i + 1).getsize() >= 3)
	    {
	    	int succ = findsuccessor(x, key);
	    	x.deletekey(i);
	    	x.addkey(succ);
	    	delete(x.getchild(i + 1), succ);
	    } else
	    {
	    	merge(x, i);
	    	delete(x.getchild(i), key);
	    }
	}
		
		
		
//		if (x.getkey(x.findkey(key)) == key)
//		{
//		
//			// x is an internal node
//			if (!x.isleaf())
//			{
//				Node predecessor = predecessor(x, key);
//				Node successor = successor(x, key);
//				
//				// predecessor has more than 2 keys
//				if (predecessor.getsize() > 2)
//				{
//					int k_ = findpredecessor(x, key);
//					movekey(k_, predecessor, x);
//					movekey(key, x, successor);
//					deletekey(successor, key);
//				}
//				// successor has more than 2 keys
//				else if (successor.getsize() > 2)
//				{
//					int k_ = findsuccessor(x, key);
//					movekey(k_, successor, x);
//					movekey(key, x, predecessor);
//					deletekey(predecessor, key);
//				}				
//				// nor predecessor and successor has only 2 keys
//				else
//				{
//					int k_ = findsuccessor(x, key);
//					movekey(k_, successor, x);
//					movekey(key, x, successor);
//					deletekey(successor, key);
//				}
//			}
//			// x is leaf node
//			else
//			{
//				Node parent = x.getparent();
//				Node sibling = findsibling(x);
//
//				// simply remove key from leaf node
//				if (x.getsize() > 2)
//					x.deletekey(x.findkey(key));
//				// sibling has more than 2 keys
//				else if (sibling.getsize() > 2)
//				{
//					int k_ = sibling.getkey(sibling.getsize() - 1);
//					movekey(parent.getkey(parent.findkey(key) - 1), parent, x);
//					movekey(k_, sibling, parent);
//					deletekey(x, key);
//				}
//				// sibling has 2 keys -> merge
//				else
//				{
//					int ind = parent.findkey(x.getkey(0));
//					if(parent.getsize() < 2)
//					{
//						movekey(parent.getkey(ind), parent, x);
//						mergenode(x, sibling);
//					} else if (parent == root)
//					{
//						
//					}
//					
//				}
//			}
//		} else
//		{
//			deletekey(x.getchild(x.findkey(key)), key);
//		}


	public static Node predecessor(Node x, int k)
	{
		int i = x.findkey(k);
		if (x.getchild(i).isleaf() || x.getchild(i) == null)
			return x.getchild(i);
		else
			return predecessor(x.getchild(i), k);
	}

	public static Node successor(Node x, int k, boolean leftchild)
	{
		int i = x.findkey(k);
		if (leftchild == true)
		{
			if (x.getchild(i).isleaf() || x.getchild(i) == null)
				return x.getchild(i);
			else
				return successor(x.getchild(i + 1), k, false);
		} else
		{
			if (x.getchild(0).isleaf() || x.getchild(i) == null)
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
		int j = n2.getkey(0);
		for (int i = 0; i < n2size; i++)
			n1.addkey(n2.deletekey(0));
		n2.getparent().deletechild(n2.getparent().findkey(j));
	}

	public static void removekey(int k, Node n)
	{
		int i = n.findkey(k);
		n.deletekey(i);
	}

	public static int findpredecessor(Node n, int k)
	{
		Node pred = predecessor(n, k);
		return pred.getkey(pred.getsize() - 1);
	}

	public static int findsuccessor(Node n, int k)
	{
		Node succ = successor(n, k, true);
		return succ.getkey(0);
	}
	
}
