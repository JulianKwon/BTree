package bTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Main extends NodeCreate{
	
	public static Node root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintTree pt = new PrintTree();
		read("rand100.txt");
		pt.print(1, root);
	}
	
	public static void read(String input)
	{
		try
		{
			File aFile = new File(input);
			FileReader fileReader = new FileReader(aFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null)
				// read line then insert number
//				System.out.println(line);
				insert(Integer.parseInt(line));
			reader.close();
		} catch (Exception ex)
		{
		}
	}
	
	public static void insert(int key)
	{
		if(root == null)
		{
			Node node = new Node();
			node.addkey(key);
			root = node;
		}
		else
		{
			if(root.isfull())
			{
				Node newn = new Node();
				
				newn.putchild(root);
				splitchild(newn, root);
				root = newn;
				simpleinsert(key, root);
			}else
				simpleinsert(key, root);
		}
	}
	
	public static void simpleinsert(int key, Node root)
	{
		int i = root.getsize() - 1;
		
		if(root.isleaf())
			root.addkey(key);
		
		else
		{
			while(i >= 0 && key < root.getkey(i))
				i--;
			i++;
			if(root.getchild(i).isfull())
			{
				splitchild(root, root.getchild(i));
				
				if(key > root.getkey(i))
					i++;				
			}
			simpleinsert(key, root.getchild(i));
		}
	}
	
	public static void splitchild(Node parent, Node child)
	{
		Node z = new Node();
		
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
		
		parent.putchild(z);
	}
	

}
