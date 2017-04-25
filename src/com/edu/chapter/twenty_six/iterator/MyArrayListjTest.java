package com.edu.chapter.twenty_six.iterator;

import java.util.Iterator;

public class MyArrayListjTest {

	public static void main(String[] args) {
		MyArrayList<String> list = new MyArrayList<String>();

		list.add("Red");
		list.add("Yellow");
		list.add("Green");
		list.add("Blue");
		list.add("Pink");

		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
