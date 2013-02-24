

public class Deque<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node * next;
	}
	
	public Deque()
	public boolean isEmpty()
	public int size()
	public void addFirst(Item item)
	public void addLast(Item item)
	public Item removeFirst()
	public Item removeLast()
	public Iterator<Item> iterator()
}