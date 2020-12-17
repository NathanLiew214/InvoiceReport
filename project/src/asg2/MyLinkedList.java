/**
Name: Nathan Liew & Dinesh Budhathoki
Program: MyLinkList.java
Purpose : Methods used for LinkedList
**/

package asg2;

import java.util.Comparator;


public class MyLinkedList<T> {
	
	private Node<T> head;
	private int size;
	private Comparator<T> value;

	public MyLinkedList(Comparator<T> value) {
		this.head = null;
		this.size = 0;
		this.value = value;
	}
	
	
	public void insertAtStart(T item){
	Node<T> temp = new Node<T>(item); 
	temp.setNext(this.head); 
	this.head = temp; 
	this.size++;
	}

	public int getsize() {
		return this.size;
	}

	public int getCount() {
		if (this.head == null) {
			return 0;
		}
		int count = 1;
		Node<T> curr = this.head;
		while (curr.hasNext()) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}

	public T retrieveItemAtIndex(int index) {
		return retrieveNodeAtIndex(index).getItem();
	}

	private Node<T> retrieveNodeAtIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index is not valid!!");
		}
		Node<T> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	public void insert(T item)
	{

		Node<T> newNode = new Node<T>(item);
		Node<T> current = this.head;
		Node<T> previous = null;

		if (size == 0)
		{
			this.head = newNode;
			size++;
		}

		else if (value.compare(newNode.getItem(), this.head.getItem()) >= 0)
		{
			Node<T> temp = this.head;
			newNode.setNext(temp);
			this.head = newNode;
			size++;
		}

		else if (size != 0)
		{
			
			while (current != null && value.compare(item, current.getItem()) < 0)
			{
				previous = current;
				current = current.getNext();
			}

			this.size++;

			newNode.setNext(current);
			previous.setNext(newNode);
		}

	}
	public void insertAtIndex(int index, T item) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index is not valid!!");
		}
		if (index == 0) {
			this.insertAtStart(item);
			return;
		}
		Node<T> curr = this.retrieveNodeAtIndex(index - 1);
		Node<T> newNode = new Node<T>(item);
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
		size++;
	}

	public T removeAtIndex(int index) {
		if (this.head == null) {
			throw new IllegalArgumentException("No element to remove!!!");
		}
		if (index == 0) {
			return this.removeFromStart();
		}
		Node<T> prev = this.retrieveNodeAtIndex(index - 1);
		Node<T> curr = prev.getNext();
		prev.setNext(curr.getNext());
		this.size--;
		return curr.getItem();
	}

	public String toString() {
		if (this.head == null) {
			return "Empty List!!";
		}
		StringBuilder sb = new StringBuilder();
		Node<T> curr = this.head;
		while (curr != null) {
			sb.append(curr.getItem().toString() + ", ");
			curr = curr.getNext();
		}
		return sb.toString();
	}

	public boolean empty() {
		return this.size == 0;
	}

	public void insertAtEnd(T item) {
		if (this.size == 0) {
			this.insertAtStart(item);
			return;
		}
		Node<T> curr = this.head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		Node<T> newEnd = new Node<T>(item);
		curr.setNext(newEnd);
		this.size++;
	}

	public T removeFromStart() {
		if (this.empty()) {
			throw new IllegalStateException("You are removing from an empty list!!!");
		}
		T item = this.head.getItem();
		this.head = this.head.getNext();
		this.size--;
		return item;
	}
	


	
	public static Comparator<Invoice> InvoiceValueComparator = new Comparator<Invoice>()	{

		public int compare(Invoice invoice1, Invoice invoice2){

			Double value1 = invoice1.getTotal();
			Double value2 = invoice2.getTotal();

			// highest to lowest order
			return value1.compareTo(value2);

		}

	};
	
}