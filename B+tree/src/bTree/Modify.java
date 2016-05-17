package bTree;

public class Modify extends NodeCreate
{
	Node temp;
	
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
			// allocate new node as new root
			Node s = new Node();
			s.putchild(r);
			r.putparent(s);
			split_child(1, r);
			insertNonfull(key, s);
			root = s;
		} else
		{
			if(root == null)
				initialize();
			insertNonfull(key, r);
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
		// new node for old node's(y's) sibling
		// move half of keys from y to z
		for (int i = 2; i < 4; i++)
			z.addkey(y.deletekey(i));
		
		// move middle key(in this case, key[1]) to parent
		x.addkey(y.deletekey(1));
		
		// if y is not a leaf, move children, too.
		if (!y.chk_leaf())
		{
			Node c = null;
			for (int i = 2; i < 5; i++)
			{
				c = y.deletechild(i);
				z.putchild(c);
				c.putparent(z);
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
