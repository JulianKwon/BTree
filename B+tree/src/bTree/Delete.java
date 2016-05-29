package bTree;

public class Delete extends NodeCreate
{
	static Node root = Main.root;
	
	public static int findkey(Node x, int k)
	{
	    int idx = 0;
	    while (idx < x.getsize() && x.getkey(idx) < k)
	        ++idx;
	    return idx;
	}
	
	public static void delete(int key)
	{
		if(root == null)
			System.out.println("트리가 비었습니다.");
		
		delete(root, key);
		
		if(root.getsize() == 0)
		{
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
		int i = findkey(x, key);
		int n = x.getsize();
		
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
		// key is not in x
	    else
	    {
	    	boolean last = ((i == n)? true : false);
	    	
	    	if (findx(root, key).getsize() < 3 && x.getchild(i).getsize() < 3)
	    		fill(x, i);
	    	
	    	if (last && findkey(x, key) > x.getsize())
	    		delete(x.getchild(findkey(x, key) - 1), key);
	    	else
	    		delete(x.getchild(findkey(x, key)), key);
	    }
	}
	
	public static void deletefromnonleaf(Node x, int key)
	{
		int i = findkey(x, key);

		
		if (x.getchild(i).getsize() >= 3)
	    {
	        int pred = findpredecessor(x, i);
	        x.deletekey(i);
	        x.addkey(pred);
	        delete(x.getchild(i), pred);
	    } else if (x.getchild(i + 1).getsize() >= 3)
	    {
	    	int succ = findsuccessor(x, i);
	    	x.deletekey(i);
	    	x.addkey(succ);
	    	delete(x.getchild(i + 1), succ);
	    } else
	    {
	    	merge(x, i);
	    	delete(x.getchild(i), key);
	    }
	}
	
	public static Node findx(Node root, int key)
	{
		int i = findkey(root, key);
		if(root.getkey(i) == key)
			return root;
		else
			return findx(root.getchild(i), key);
	}
	
	public static void merge(Node x, int idx)
	{
		Node leftc = x.getchild(idx);
		Node rightc = x.getchild(idx + 1);
		int n = rightc.getsize();
		
		// pull key from parent x
		leftc.addkey(x.deletekey(idx));
		for(int i = 0 ; i < n; i++)
			leftc.addkey(rightc.deletekey(0));
		
		// move children from right to left child
		if(!leftc.isleaf())
		{
			for(int i = 0 ; i <= n; i++)
				leftc.putchild(rightc.deletechild(0));
		}
		
		x.deletechild(idx + 1);
	}
	
	public static void fill(Node x, int idx)
	{
		if (idx != 0 && x.getchild(idx - 1).getsize() >= 3)
			borrowfromprev(x, idx);
		else if (idx != x.getsize() && x.getchild(idx + 1).getsize() >= 3)
			borrowfromsucc(x, idx);
		else
		{
			if(idx != x.getsize())
				merge(x, idx);
			else
				merge(x, idx - 1);
		}
	}
	
	public static void borrowfromprev(Node x, int idx)
	{
		Node child = x.getchild(idx);
		Node leftc = x.getchild(idx - 1);
		int n = leftc.getsize();
		
		// move left key from parent to child that includes key k
		child.addkey(x.deletekey(idx - 1));
		
		// move left child's last key up to parent
		x.addkey(leftc.deletekey(leftc.getsize() - 1));
		
		// move left child's last child to child that includes key k
		if (!child.isleaf())
			child.putchild(leftc.deletechild(n));
	}
	
	public static void borrowfromsucc(Node x, int idx)
	{
		Node child = x.getchild(idx);
		Node rightc = x.getchild(idx + 1);
		
		// move right key from parent to child that includes key k
		child.addkey(x.deletekey(idx));
		
		// move right child's first key up to parent
		x.addkey(rightc.deletekey(0));
		
		// move right child's first child to child that includes key k
		if (!child.isleaf())
			child.putchild(rightc.deletechild(0));
	}

	public static void movekey(int key, Node n1, Node n2)
	{
		int i = findkey(n1, key);
		n2.addkey(n1.deletekey(i));
	}

	public static void removekey(int k, Node n)
	{
		int i = findkey(n, k);
		n.deletekey(i);
	}

	public static int findpredecessor(Node n, int idx)
	{
		Node child = n.getchild(idx);
		while (!child.isleaf())
			child = child.getchild(child.getsize());
		return child.getkey(child.getsize() - 1);
	}

	public static int findsuccessor(Node n, int idx)
	{
		Node child = n.getchild(idx + 1);
		while (!child.isleaf())
			child = child.getchild(0);
		return child.getkey(0);
	}
	
}
