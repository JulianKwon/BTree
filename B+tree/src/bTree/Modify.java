package bTree;

import bTree.NodeCreate.Node;

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
		while(root.getkey(i) > key)
			i++;
		if(chk_leaf(root))
			return root;
		else
			root = Search(key, root.getchild(i));
		return root;
	}
	
	public void insert(int key)
	{
		insert(key, root);
	}

	public void insert(int key, Node roo)
	{
		if (roo == null)
		{
			roo = new Node();
			roo.addkey(key);
		} else
		{
			Node original = Search(key, roo);
			if (original.isfull()) // roo is full
				roo = split_child(key, original);
			else
				original.addkey(key);
		}
	}

	public Node split_child(int key, Node ro)
	{
		Node parent = ro.getparent();
		Node newro = new Node();
		Node newn = new Node();
		Node rot = null;
		if (parent == null)
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
			rot = newro;
		} else
		{
			if(!parent.isfull())
			{
				
			}
		}
		return rot;
	}
}
