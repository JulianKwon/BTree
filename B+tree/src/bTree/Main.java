package bTree;

public class Main
{
	public Node root;
	
	public class Node{
		private int[] k = new int[4];
		private Node[] c = new Node[5];
		private Node parent;
		private int lenk; //k �迭�� �󸶳� á����
		private int lenc;
		public Node(int k){
			this.k[lenk] = k;
			this.lenk++;
		}
		public int[] getk(){
			return k;
		}
		public Node[] getc(){
			return c;
		}
		public Node getparent(){
			return parent;
		}
		public int getlenk(){
			return lenk;
		}
		public int getlenc(){
			return lenc;
		}
	}
	
	public static void main(String[] args)
	{
		
	}
}
