package edu.tree.red.black;

/**
 * 红黑树测试
 */
public class RBTreeTest {

    public static void main(String[] args) {
        Integer[] array =
            {26, 17, 41, 14, 21, 30, 47, 10, 16, 19, 23, 28, 38, 7, 12, 15, 20, 35,39, 3};
        RBTree<Integer> tree = new RBTree<Integer>(array);
        tree.delete(26);
    }

}
