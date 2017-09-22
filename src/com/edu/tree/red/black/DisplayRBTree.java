package com.edu.tree.red.black;

import javax.swing.JApplet;

/**
 * @author xukai
 * @date 2017年4月17日 下午12:57:09
 *
 */
public class DisplayRBTree extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5329743141395531185L;

	public DisplayRBTree() {
		Integer[] array = {11};
		RBTree<Integer> tree = new RBTree<Integer>(array);
		System.out.println(tree);
		add(new RBTreeJPanel(tree));
	}

}
