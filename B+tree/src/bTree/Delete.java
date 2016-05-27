package bTree;

public class Delete extends NodeCreate
{
	static Node root = Main.root;

	public static void deletekey(Node x, int key)
	{
		int i = x.getsize();
		Node predecessor = predecessor(x, key);
		Node sibling = predecessor.getparent().getchild(predecessor.getparent().getsize() - 1);

		// if key is in x
		if (x.getkey(x.findkey(key)) == key)
		{
			if (!x.isleaf())
			{
				// when x's size is smaller than 2
				if (x.getsize() < 2)
				{
					deletefromnode(key, x);
					if (sibling.getsize() > 2)
						movekey(predecessor.getkey(predecessor.getsize() - 1), predecessor, x);
					else
					{
						movekey(predecessor.getkey(predecessor.getsize() - 1), predecessor, x);
						mergenode(sibling, predecessor);
						movekey();
					}
				} else
				{
					
				}
			} 
			// x is leaf node
			else
			{
				
			}
		} else
		{
			deletekey(x.getchild(x.findkey(key)), key);
		}

	}

	public static void deletefromnode(int key, Node n)
	{

	}

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
		int i = n2.getkey(0);

		for (int i = 0; i < n2size; i++)
			n1.addkey(n2.deletekey(0));
		n2.getparent().resetchild(n2.getparent().findkey(i));

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
