package com.edu.chapter.twenty_six.iterator;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {

	public static void main(String[] args) {
		MyLinkedList<String> list = new MyLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list.contains("d"));
		System.out.println(list.indexOf("c"));
		System.out.println(list.indexOf("e"));
		System.out.println(list.get(4));
		System.out.println(list.indexOf(null));
		System.out.println(list.set(3, "f"));
		System.out.println(list + "," + list.getFirst() + "," + list.getLast());
	}
	
	private Node<E> first;
	
	private Node<E> last;
	
	public MyLinkedList() {
		super();
	}
	
	public MyLinkedList(MyLinkedList<E> list, E[] elements) {
		super(elements);
	}
	
	public E getFirst() {
		if (this.size == 0) {
			return null;
		}
		return this.first.element;
	}
	
	public E getLast() {
		if (this.size == 0) {
			return null;
		}
		return this.last.element;
	}
	
	/**
	 * 
	 * <p>Title: addFirst</p>
	 * <p>author : xukai</p>
	 * <p>date : 2017年4月26日 上午11:55:04</p>
	 * @param e
	 */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e);
		newNode.next = this.first;
		this.first = newNode;
		this.size += 1;
		
		// just one elemet,(last==first) = true
		if (this.last == null) {
			this.last = this.first;
		}
	}
	
	public void addLast(E e) {
		if (this.last == null) {
			this.first = (this.last = new Node<E>(e));
		} else {
			this.last.next = new Node<E>(e);
			this.last = this.last.next;
		}
		
		this.size += 1;
	}
	
	
	@Override
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		} else if (index >= this.size) {
			addLast(e);
		} else {
			Node<E> current = this.first;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			current.next.next = temp;
			this.size += 1;
		}
	}
	
	public E removeFirst() {
		if (this.size == 0) {
			return null;
		}
		Node<E> temp = this.first;
		this.first = this.first.next;
		this.size -= 1;
		if (this.first == null) {
			this.last = null;
		}
		return temp.element;
	}
	
	public E removeLast() {
		if (this.size == 0) {
			return null;
		}
		
		if (this.size == 1) {
			Node<E> temp = this.first;
			this.first = (this.last = null);
			this.size = 0;
			return temp.element;
		}
		
		Node<E> current = this.first;
		for (int i = 0; i < size - 2; i++) {
			current = current.next;
		}
		Node<E> temp = this.last;
		this.last = current;
		this.last.next = null;
		this.size -= 1;
		return temp.element;
	}

	@Override
	public E remove(int index) {
		if ((index < 0) || index >= this.size) {
			return null;
		} 
		if (index == 0) {
			return removeFirst();
		}
		if (index == this.size - 1) {
			return removeLast();
		}
		
		Node<E> previous = this.first;
		for (int i = 0; i < index; i++) {
			previous = previous.next;
		}
		
		Node<E> current = previous.next;
		previous.next = current.next;
		this.size -= 1;
		return current.element;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("[");
		Node<E> current = this.first;
		for (int i = 0; i < this.size; i++) {
			result.append(current.element);
			current = current.next;
			
			if (current != null) {
				result.append(", ");
			} else {
				result.append("]");
			}
		}
		return result.toString();
	}

	@Override
	public void clear() {
		this.first = (this.last = null);
	}
	
	@Override
	public boolean contains(E e) {
		return indexOf(e) != -1;
	}
	
	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.size)) {
			return null;
		}
		
		Node<E> current = this.first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;
	}
	
	@Override
	public int indexOf(E e) {
		int index = 0;
		if (e == null) {
			for (Node<E> node = this.first; node != null; node = node.next) {
				if (node.element == null) {
					return index;
				}
				index ++;
			}
		} else {
			for (Node<E> node = this.first; node != null; node = node.next) {
				if (node.element.equals(e)) {
					return index;
				}
				index ++;
			}
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(E e) {
		int index = -1;
		int cursor = 0;
		for (Node<E> node = this.first; node != null; node = node.next) {
			if (node.element.equals(e)) {
				index = cursor;
			}
			cursor ++;
		}
		return index;
	}

	@Override
	public Object set(int index, E e) {
		if (index < 0 || index >= this.size) {
			return null;
		}
		Node<E> current = this.first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.element = e;
		return current;
	}

	private static class Node<E> {
		E element;
		
		Node<E> next;
		
		public Node(E element) {
			this.element = element;
		}
		
	}
	
	public Iterator<E> iterator() {
		return new MyLinkedListIterator(this);
	}

	class MyLinkedListIterator implements Iterator<E> {

		private MyLinkedList<E> list = new MyLinkedList<>();
		
		private MyLinkedList<E> originaList;
		
		private int current = 0;
		
		public MyLinkedListIterator(MyLinkedList<E> list) {
			this.originaList = list;
			for (int i = 0; i < list.size; i++) {
				this.list.add(originaList.get(i));
			}
		}
		
		@Override
		public boolean hasNext() {
			return this.current < this.list.size;
		}

		@Override
		public E next() {
			return this.list.get(this.current++);
		}

		@Override
		public void remove() {
			this.list.remove(this.current);
			this.originaList.remove(this.current);
		}
		
	}
	
}
