package bTree;

public class NodeCreate
{
	public Node root;
	
	public class Node{
		private int[] k = new int[4];
		private Node[] c = new Node[5];
		private Node parent;
		private int lenk; //k 배열에 얼마나 찼는지
		private int lenc;
		public Node(int k){
			this.k[lenk] = k;
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
		public void putlenk(int input){
			lenk = input;
		}
		public void putlenc(int input){
			lenc = input;
		}
	}
}
