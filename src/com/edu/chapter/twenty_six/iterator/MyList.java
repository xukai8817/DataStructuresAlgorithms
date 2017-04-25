package com.edu.chapter.twenty_six.iterator;

public interface MyList<E> {

	public abstract void add(E e);

	public abstract void add(int index, E e);

	public abstract void clear();

	public abstract boolean contains(E e);

	public abstract E get(int index);

	public abstract int indexOf(E e);

	public abstract boolean isEmpty();

	public abstract int lastIndexOf(E e);

	public abstract boolean remove(E e);

	public abstract E remove(int index);

	public abstract Object set(int index, E e);

	public abstract int size();

}
