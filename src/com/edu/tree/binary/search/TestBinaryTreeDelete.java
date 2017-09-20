package com.edu.tree.binary.search;

/**
 * @author xukai
 * @date 2017年4月17日 上午11:13:06
 *
 */
public class TestBinaryTreeDelete {

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<String>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");
        
        System.out.println("delete George:");
        tree.delete("George");
        printTree(tree);
        
        System.out.println("delete Adam:");
        tree.delete("Adam");
        printTree(tree);
        
        System.out.println("delete Michael:");
        tree.delete("Michael");
        printTree(tree);
        
    }
    
    public static void printTree(BinaryTree<?> tree) {
        System.out.println("Inorder: ");
        tree.inorder();
        System.out.println("\nPostorder: ");
        tree.postorder();
        System.out.println("\nPreorder: ");
        tree.preorder();
        System.out.println("\nsize=" + tree.getSize());
    }
    
}
