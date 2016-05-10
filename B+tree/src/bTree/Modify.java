package bTree;

public class Modify extends Main
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
			root = new Node(key);
		}
	}
}
