package com.edu.chapter.twenty_six.day0416;

import java.util.ArrayList;

public class MyStack<E> extends ArrayList<E> {

	/**
	 * TODO 变量名称 
	 */
	private static final long serialVersionUID = 3841535215499486992L;

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	public int getSize() {
		return size();
	}
	
	/**
	 * 获取栈顶元素
	 */
	public E peek() {
		return get(getSize() - 1);
	}
	
	/**
	 * 删除并获取栈顶元素
	 */
	public E pop() {
		E e = get(getSize() - 1);
		remove(getSize() - 1);
		return e;
	}
	
	/**
	 * 向栈里添加E
	 */
	public E push(E e){
		add(e);
		return e;
	}
	
	public int search(E e) {
		return indexOf(e);
	}
	
	public String toString() {
		return "Stack" + toString();
	}
}
