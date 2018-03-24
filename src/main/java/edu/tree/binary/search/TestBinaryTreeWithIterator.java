package edu.tree.binary.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xukai
 * @date 2017年4月17日 上午11:13:06
 *
 */
public class TestBinaryTreeWithIterator {

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<String>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");
        
        Iterator<String> iterator = tree.inorderIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toUpperCase());
        }
        
        List<?> list = new ArrayList<>();
        list.iterator();
        
    }
    
}
