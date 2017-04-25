package com.edu.chapter.twenty_six.iterator;

import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

	public static final int INITIAL_CAPACITY = 16;
	
	private Object[] data = new Object[INITIAL_CAPACITY];
	
	public MyArrayList() {
	}
	
	public MyArrayList(E[] e) {
		data = e;
		size = e.length;
	}
	
	@Override
	public void add(int index, E e) {
		ensureCapacity();
		
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		
		data[index] = e;
		size ++;
	}

	private void ensureCapacity() {
		if (size >= data.length) {
			Object[]  newData = new Object[data.length * 2];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
	}
	
	@Override
	public void clear() {
		data = new Object[INITIAL_CAPACITY];
	}

	@Override
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		return (E) data[index];
	}

	@Override
	public int indexOf(E e) {
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		for (int i = size - 1; i >= 0; i--) {
			if (e.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		Object o = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i+1];
		}
		size --;
		return (E) o;
	}

	@Override
	public Object set(int index, E e) {
		Object old = data[index];
		data[index] = e;
		return old;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("[");
		
		for (int i = 0; i < size; i++) {
			result.append(data[i]);
			if (i < size - 1) {
				result.append(", ");
			}
		}
		return result.toString() + "]";
	}
	
	public Iterator<E> iterator() {
		return new MyArrayListsIterator(this);
	}
	
	class MyArrayListsIterator implements Iterator<E>{

		private MyArrayList<E> list = new MyArrayList<>();
		
		private int current = 0;
		
		private MyArrayList<E> originalList;
		
		public MyArrayListsIterator(MyArrayList<E> originalList) {
			this.originalList = originalList;
			for (int i = 0; i < originalList.size(); i++) {
				list.add(originalList.get(i));
			}
		}
		
		@Override
		public boolean hasNext() {
			if (current < list.size()) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}

		@Override
		public void remove() {
			list.remove(current);
			originalList.remove(current);
		}
		
	}
}
