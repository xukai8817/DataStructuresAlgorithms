package com.edu.tree.binary.search;

import javax.swing.JApplet;

/**
 * @author xukai
 * @date 2017年4月17日 下午12:57:09
 *
 */
public class DisplayBinaryTree extends JApplet {

    /**
     * 
     */
    private static final long serialVersionUID = 5329743141395531185L;

    public DisplayBinaryTree() {
//        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        Integer[] numbers = {5, 3, 7, 4, 2, 8, 6};
        BinaryTree<Integer> intTree = new BinaryTree<>(numbers);
        System.out.println(intTree.size + "," +intTree.height());
        System.out.println(intTree.isFullBinaryTree());
        add(new TreeControl(intTree));
    }
    
}
