package com.edu.chapter.twenty_six.day0416;

import java.util.ArrayList;

import com.edu.chapter.twenty_six.day0416.BinaryTree.TreeNode;

/**
 * @author xukai
 * @date 2017年4月16日 下午10:22:50
 *
 */
public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<String>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder (sorted): ");
        tree.postorder();
        System.out.print("\nPreorder (sorted): ");
        tree.preorder();
        System.out.println("The number of nodes is " + tree.getSize());
        System.out.println(tree.search("Peter"));
        ArrayList<TreeNode<String>> path = tree.path("Peter");
        System.out.println(path);
        
        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        BinaryTree<Integer> intTree = new BinaryTree<>(numbers);
        intTree.inorder();
        System.out.println();
        System.out.println(intTree.path(2));
        System.out.println(intTree.root);
        
        intTree.breadthFirstTraversal();
        System.out.println(intTree.height());
        System.out.println(intTree.isFullBinaryTree());
    }
    
}
