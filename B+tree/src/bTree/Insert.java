package bTree;

public class Insert extends NodeCreate
{

	public static void insert(int key)
	{
		Node root = Main.root;

		// initialize
		if (root == null)
		{
			Node node = new Node();
			node.addkey(key);
			root = node;
		} else
		{
			// if the root node is full
			if (root.isfull())
			{
				Node newn = new Node();
				newn.putchild(root);
				newn.putisleaf(false);

				// split and insert key
				splitchild(newn, root);
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
		while (i >= 0 && key < root.getkey(i))
			i--;
		i++;
		if(root.getchild(i).isleaf())
		{
			root.getchild(i).addkey(i);
			if(root.getchild(i).isfull())
				splitchild(root, root.getchild(i));
		}
		else
		{
			if (root.getchild(i).isfull())
			{
				splitchild(root, root.getchild(i));

				if (key > root.getkey(i))
					i++;
			}
			simpleinsert(key, root.getchild(i));
		}
	}

	public static void splitchild(Node parent, Node child)
	{
		// create new node for sibling of node child
		Node z = new Node();

		// z's leaf is y's leaf(boolean)
		z.putisleaf(child.isleaf());
		
		for(int i = 0 ; i < 2;i++)
			z.addkey(child.deletekey(3));
		
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
	}
}
