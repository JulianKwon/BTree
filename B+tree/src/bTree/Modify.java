package bTree;

public class Modify extends NodeCreate
{
	Node temp;

	public boolean chk_leaf(Node r)
	{
		for (int i = 0; i < 5; i++)
		{
			// if leaf, there's no child
			if (r.getchildsize() != 0) 
			{
				return false;
			}
		}
		return true;
	}

	public Node Search(int key, Node root)
	{
		int i = 0;
		// search for the position in key array
		// of root node
		while (root.getkey(i) > key && !root.isfull())
			i++;
		if (chk_leaf(root) || (root.isfull() && chk_leaf(root)))
			return root;
		else
			root = Search(key, root.getchild(i));
		return root;

	}

	public void insert(int key)
	{
		insert(key, root);
	}

	public void insert(int key, Node root)
	{
		if (root.isfull())
		{
			Node s = new Node();
			s.putchild(root);
			 splitchild(s, 1, root);
			 insertNonfull(key, s);
			 root = s;
		} else
		{
			 insertNonfull(key, root);
		}
	}

	public Node insertNonfull(int key, Node root)
	{
		int i = root.getsize();
		
		if (chk_leaf(root))
		{
			while(i >= 1 && key < root.getchild())
		} else
		{
			Node innode = Search(key, root);
			if (innode.isfull())
				split_child(key, innode);
			else
				innode.addkey(key);
			 return root;
		}

	}

	public void split_child(int key, Node ro)
	{
		Node parent = ro.getparent();
		Node newro = new Node();
		Node newn = new Node();

		// if ro is root node
		if (parent == null)
		{
			if (key < ro.getkey(1))
			{
				int k = ro.deletekey(1);
				// add key to the new parent node
				newro.addkey(k);
				// move keys from original node to
				// new sibling node
				for (int i = 0; i < 2; i++)
					newn.addkey(ro.deletekey(i + 2));
				// move childtrees from original node to new
				// sibling node
				for (int i = 2; i < 5; i++)
					newn.putchild(ro.deletechild(i));

				ro.putparent(newro);
				newn.putparent(newro);
				// put original node and new node as
				// children of new root node
				newro.putchild(ro);
				newro.putchild(newn);
				ro.addkey(key);
			} else if (key > ro.getkey(2) || key > ro.getkey(1) && key < ro.getkey(2))
			{
				int k = ro.deletekey(2);
				newro.addkey(k);
				// move keys from original node to
				// new sibling node
				newn.addkey(ro.deletekey(3));
				// move childtrees from original
				// node to new sibling node
				for (int i = 3; i < 5; i++)
				{
					newn.putchild(ro.getchild(i));
					ro.deletechild(i);
				}
				ro.putparent(newro);
				newn.putparent(newro);
				newro.putchild(ro);
				newro.putchild(newn);
			}
			// reassign new root as root
			root = newro;
		}
		// ro is not root node
		else
		{
			int k = -1;
			if (key < ro.getkey(1))
			{
				k = ro.deletekey(1); // add key to the parent node
				for (int i = 0; i < 2; i++) // move keys from original node to
											// new sibling node
				{
					newn.addkey(ro.getkey(i + 2));
					ro.deletekey(i + 2);
				}
				for (int i = 2; i < 5; i++)
				{ // move childtrees from original node to new sibling node
					newn.putchild(ro.getchild(i));
					ro.deletechild(i);
				}
				ro.putparent(parent);
				newn.putparent(parent);
				parent.putchild(ro);
				parent.putchild(newn);
				ro.addkey(key);
			} else if (key > ro.getkey(2) || key > ro.getkey(1) && key < ro.getkey(2))
			{
				k = ro.deletekey(2);
				parent.addkey(k);
				newn.addkey(ro.getkey(3)); // move keys from original node to
											// new sibling node
				newn.deletekey(3);
				for (int i = 3; i < 5; i++) // move childtrees from original
											// node to new sibling node
				{
					newn.putchild(ro.getchild(i));
					ro.deletechild(i);
				}
				ro.putparent(parent);
				newn.putparent(parent);
				parent.putchild(ro);
				parent.putchild(newn);
				parent.addkey(key);
			}
			if (parent.isfull()) // if there is a propagation, recursively split
									// parent node
				split_child(k, parent);
			else
				parent.addkey(k);
		}

	}
}
