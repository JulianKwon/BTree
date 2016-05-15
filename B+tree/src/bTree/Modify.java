package bTree;

public class Modify extends NodeCreate
{
	Node temp;
	
	
	
	public boolean chk_leaf(Node r)
	{
		for (int i = 0; i < 5; i++)
		{
			if (r.getchildsize() != 0) // if leaf, there's no child
			{
				return false;
			}
		}
		return true;
	}
	
	public Node Search(int key, Node root)
	{
		int i = 0;
		while(root.getkey(i) > key && !root.isfull())
			i++;
		if(chk_leaf(root) || root.isfull())
			return root;
		else
			root = Search(key, root.getchild(i));
		return root;
	}
	
	public void insert(int key)
	{
		root = insert(key, root);
	}

	public Node insert(int key, Node root)
	{
		if (root == null)
		{
			root = new Node();
			root.addkey(key);
			return root;
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
		if (parent == null) // if ro is root node
		{
			if (key < ro.getkey(1))
			{
				int k = ro.getkey(1);
				newro.addkey(k); // add key to the new parent node
				for(int i = 0; i < 2; i++) // move keys from original node to new sibling node
				{
					newn.addkey(ro.getkey(i + 2));
					ro.deletekey(i + 2);
				}
				for(int i = 2; i < 5; i++){ //move childtrees from original node to new sibling node
					newn.putchild(ro.getchild(i));
					ro.deletechild(i);
				}
				ro.putparent(newro);
				newn.putparent(newro);
				newro.putchild(ro);
				newro.putchild(newn);
				ro.addkey(key);
			} else if (key > ro.getkey(2) || key > ro.getkey(1) && key < ro.getkey(2)) 
			{
				int k = ro.getkey(2);
				newro.addkey(k);
				newn.addkey(ro.getkey(3)); //move keys from original node to new sibling node
				newn.deletekey(3);
				for(int i = 3; i < 5; i++) //move childtrees from original node to new sibling node
				{
					newn.putchild(ro.getchild(i));
					ro.deletechild(i);
				}
				ro.putparent(newro);
				newn.putparent(newro);
				newro.putchild(ro);
				newro.putchild(newn);
				newro.addkey(key);
			}
			if(ro == root)//reassign new root as root
				root = newro;
		} else // if ro is not root node
		{
			if(!parent.isfull()) //propagation is not happening
			{
				if (key < ro.getkey(1))
				{
					int k = ro.getkey(1);
					parent.addkey(k); // add key to the parent node
					for(int i = 0; i < 2; i++) // move keys from original node to new sibling node
					{
						newn.addkey(ro.getkey(i + 2));
						ro.deletekey(i + 2);
					}
					for(int i = 2; i < 5; i++){ //move childtrees from original node to new sibling node
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
					int k = ro.getkey(2);
					parent.addkey(k);
					newn.addkey(ro.getkey(3)); //move keys from original node to new sibling node
					newn.deletekey(3);
					for(int i = 3; i < 5; i++) //move childtrees from original node to new sibling node
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
			} else //if there is a propagation, recursively split parent node
				split_child(key, parent);
		}
	}
}
