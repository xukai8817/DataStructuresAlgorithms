package edu.tree.red.black;

/**
 * @author xukai
 * 红黑树应用
 */
public class RedBlackTreeApplication {

	public static void main(String[] args) {
		Integer[] array = { 11, 2, 22, 1, 13,  19, 12, 17 };
		RBTree<Integer> tree = new RBTree<Integer>(array);
		tree.delete(11);
		System.out.println(tree.toJSON());
	}
	
}
