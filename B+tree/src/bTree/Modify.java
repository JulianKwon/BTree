package bTree;

public class Modify extends NodeCreate
{
	
	public boolean chk_leaf(Node r){
		for(int i = 0 ; i < 5; i++){
			if(r.getc() != null){
				return false;
			}
		}
		return true;
	}
	
	public void insert(int key)
	{
		if (root == null)
		{
			root.putlenc(0);
			root = new Node(key);
			int in = root.getlenk() + 1;
			root.putlenc(in);
		} else
		{
			if (root.getlenc() == 4){ //root is full
				if (key < root.getk()[0])
				{
					Node s = new Node(root.getk()[1]);
					split_child(key, root);
				}
			}
		}
	}
	public void split_child(int key, Node root)
	{
		
	}
}
