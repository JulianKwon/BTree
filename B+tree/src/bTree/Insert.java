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
		if (root.isleaf())
		{
			root.addkey(key);
			if (root.isfull())
				split(root);
		} else
		{
			while (i >= 0 && key < root.getkey(i))
				i--;
			i++;

			simpleinsert(key, root.getchild(i));
		}
	}

	public static void split(Node x)
	{
		// create new node for sibling of node x
		Node z = new Node();
		Node parent;
		// if the input node is root, we have to make new node for root's parent
		if (x == root)
		{
			Node newn = new Node();
			newn.putchild(x);
			root = newn;

			parent = x.getparent();

			// z's leaf is y's leaf(boolean)
			z.putisleaf(x.isleaf());

			for (int i = 0; i < 2; i++)
				z.addkey(x.deletekey(3));

			parent.addkey(x.deletekey(2));
			// add node z to parent
			parent.putchild(z);

			if (!x.isleaf())
				for (int i = 3; i < 6; i++)
					z.putchild(x.deletechild(3));

		} else
		{
			parent = x.getparent();

			// z's leaf is y's leaf(boolean)
			z.putisleaf(x.isleaf());

			// move half of x to z
			for (int i = 0; i < 2; i++)
				z.addkey(x.deletekey(3));

			// add midkey to parent
			parent.addkey(x.deletekey(2));

			// add node z to parent
			parent.putchild(z);

			if (!parent.isfull())
			{
				if (!x.isleaf())
					for (int i = 3; i < 6; i++)
						z.putchild(x.deletechild(3));
			} else{
				if (!x.isleaf())
					for (int i = 3; i < 6; i++)
						z.putchild(x.deletechild(3));
				split(parent);
			}

		}

	}
}
