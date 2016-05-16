package bTree;

public class Modify extends NodeCreate
{
	Node temp;

//	public Node Search(int key, Node root)
//	{
//		int i = 0;
//		// search for the position in key array
//		// of root node
//		while (root.getkey(i) > key && !root.isfull())
//			i++;
//		if (chk_leaf(root) || (root.isfull() && chk_leaf(root)))
//			return root;
//		else
//			root = Search(key, root.getchild(i));
//		return root;
//
//	}
	
	public void initialize()
	{
		root = new Node();
	}

	public void insert(int key)
	{
		insert(key, root);
	}
	
	public void insert(int key, Node r)
	{
		if (r.isfull())
		{
			Node s = new Node();
			s.putchild(r);
			r.putparent(s);
			split_child(1, r);
			insertNonfull(key, s);
			root = s;
		} else
		{
			insertNonfull(key, root);
		}
	}

	public void insertNonfull(int key, Node x)
	{
		int i = x.getsize() - 1;

		if (x.chk_leaf())
		{
			x.addkey(key);
		}
		// parameter root is not a leaf node
		else
		{
			// search position that key has to go in.
			while (i >= 0 && key < x.getkey(i))
				i--;
			i++;

			// ci = x's i_th child
			Node ci = x.getchild(i);
			// ci is full
			if (ci.isfull())
			{
				split_child(i, ci);

				if (key > x.getkey(i))
					i++;
			}
			// recursion
			insertNonfull(key, ci);
		}

	}

	public void split_child(int index, Node y)
	{
		Node x = y.getparent();
		Node z = new Node();
		// move half of keys from y to z(new node)
		for (int i = 2; i < 4; i++)
			z.addkey(y.deletekey(i));

		// if y is not a leaf, move children, too.
		if (!y.chk_leaf())
		{
			for (int i = 2; i < 5; i++)
			{
				z.putchild(y.deletechild(i));
				y.deletechild(i).putparent(z);
			}
		}
		// insert z as x's child
		//   x
		//  / \
		// y   z
		x.putchild(z);
		z.putparent(x);
		
	}
}
