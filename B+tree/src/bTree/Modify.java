package bTree;

public class Modify extends NodeCreate
{

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

	public void insert(int key)
	{
		if (root == null)
		{
			root = new Node(key);

			int in = root.getsize() + 1;

			root.putchildsize(in);
		} else
		{
			if (root.getchildsize() == 4)
			{ // root is full
				if (key < root.getkey(1)) // key <= 0 1
				{
					Node s = new Node(root.getkey(1));
					split_child(1, root);
				} else if (key > root.getkey(2)) // 2 3 =< key
				{ 

				}
			}
		}
	}

	public void split_child(int key, Node root)
	{
		Node parent = root.getparent();
		Node newn = new Node(root.getk()[key]);
		if (parent.getlenk() < 4) // root's parent is not full
		{
			for (int i = 4; i > key; i--)
			{ // split root's keys and children by degree key
				newn.getc()[i - (4 - key) - 1] = root.getc()[i];
				root.getc()[i] = null;
				if (i > key + 1)
				{
					int a = i - 1; // ****
					newn.getk()[a - (4 - key) - 1] = root.getk()[a];
					root.getk()[a] = -1;
				}
			}
			int k = root.getk()[key];
			root.getk()[key] = -1;
		}

	}
}
