package bTree;

public class Insert extends NodeCreate
{

	public static void insert(int key)
	{
		Node root = Main.root;

		if (root == null)
		{
			Node node = new Node();
			node.addkey(key);
			root = node;
		} else
		{
			if (root.isfull())
			{
				Node newn = new Node();

				newn.putchild(root);
				splitchild(newn, root, key);
				root = newn;
				simpleinsert(key, root);
			} else
				simpleinsert(key, root);
		}
		Main.root = root;
	}

	public static void simpleinsert(int key, Node root)
	{
		int i = root.getsize() - 1;

		if (root.isleaf())
			root.addkey(key);

		else
		{
			while (i >= 0 && key < root.getkey(i))
				i--;
			i++;
			if (root.getchild(i).isfull())
			{
				splitchild(root, root.getchild(i), key);

				if (key > root.getkey(i))
					i++;
			}
			simpleinsert(key, root.getchild(i));
		}
	}

	public static void splitchild(Node parent, Node child, int key)
	{
		Node z = new Node();

		if (key < child.getkey(1))
		{
			for (int i = 2; i < 4; i++)
				z.addkey(child.deletekey(2));

			parent.addkey(child.deletekey(1));

			if (!child.isleaf())
			{
				Node c = null;
				for (int i = 2; i < 5; i++)
				{
					c = child.deletechild(2);
					z.putchild(c);
				}
			}
		} else
		{
			z.addkey(child.deletekey(3));

			parent.addkey(child.deletekey(2));

			if (!child.isleaf())
			{
				Node c = null;
				for (int i = 3; i < 5; i++)
				{
					c = child.deletechild(3);
					z.putchild(c);
				}
			}
		}
		parent.putchild(z);
	}
}
