package com.edu.tree.red.black;

/**
 * @author xukai
 * 红黑树应用
 */
public class RedBlackTreeApplication {

	public static void main(String[] args) {
		Integer[] array = {4, 3, 1, 2};
		RBTree<Integer> tree = new RBTree<Integer>(array);
		System.out.println(tree);
	}
	
}
