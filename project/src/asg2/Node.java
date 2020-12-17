/**
Name: Nathan Liew & Dinesh Budhathoki
Program: Node.java
Purpose :Methods used to Link LinkedList
**/
package asg2;


public class Node<T> {
	private T item;
	private Node<T> next;

	public Node(T item) {
		this.item = item;
		this.next = null;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public boolean hasNext() {
		return this.next != null;
	}
}