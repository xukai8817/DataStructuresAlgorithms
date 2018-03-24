package main.java.edu.other.test;

import java.util.Arrays;

public class StringSpiltTest {

	public static void main(String[] args) {
		String str = "a,b,c,d,";
		System.out.println(Arrays.asList(str.split(",")));
	}
}
