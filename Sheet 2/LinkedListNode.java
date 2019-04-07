
public class LinkedListNode {
	public LinkedListNode(int x) {
		value = x;
	}
	private int value;
	private LinkedListNode next = null;
	public int getValue() {
		return value;
	}
	public LinkedListNode getNext() {
		return next;
	}
	public void setNext(LinkedListNode node) {
		next = node;
	}
		
}
