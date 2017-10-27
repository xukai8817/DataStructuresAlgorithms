package com.edu.other.test;

/**
 * @author xukai
 *
 */
public class IntegerEqualInt {
	
	public static void main(String[] args) {
		Integer integer = new Integer(123);
		System.out.println(integer == 123); // true
		
		Object i = 345;
		boolean flag = i instanceof Integer;
		System.out.println(flag);	// true
	}

}
