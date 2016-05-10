package bTree;

public class Main
{
	private class Node{
		private Entry[] children = new Entry[4];
	}
	
	private class Entry{
		private int key;
		private Node next;
		public Entry(int key, Node next){
			this.key = key;
			this.next = next;
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}
}
