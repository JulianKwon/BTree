package bTree;

public class Insert extends NodeCreate
{

	static Node root = Main.root;

	public static void insert(int key)
	{

		// initialize
		if (root == null)
		{
			Node node = new Node();
			node.addkey(key);
			root = node;
		} else
			// if root != null
			simpleinsert(key, root);
		
		Main.root = root;
	}

	public static void simpleinsert(int key, Node root)
	{
		int i = root.getsize() - 1;
		while (i >= 0 && key < root.getkey(i))
			i--;
		i++;
		if (root.isleaf())
		{
			root.addkey(key);
			if (root.isfull())
				split(root);
		} else
			simpleinsert(key, root.getchild(i));
	}

	public static void split(Node child)
	{
		// create new node for sibling of node child
		Node z = new Node();
		Node parent = child.getparent();

		// z's leaf is y's leaf(boolean)
		z.putisleaf(child.isleaf());

		for (int i = 0; i < 2; i++)
			z.addkey(child.deletekey(3));

		// add node z to parent
		parent.putchild(z);

		parent.addkey(child.deletekey(2));

		if (!child.isleaf())
		{
			Node c = null;
			for (int i = 3; i < 6; i++)
			{
				c = child.deletechild(3);
				z.putchild(c);
			}
		}
		if (parent.isfull())
		{
			if (parent == root)
			{
				Node newn = new Node();
				newn.putchild(parent);
				split(parent);
				root = newn;
				
			}else
				split(parent);
		}

	}
}
