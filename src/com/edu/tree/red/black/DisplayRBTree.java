package com.edu.tree.red.black;

import java.awt.Dimension;
import java.awt.Toolkit;

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
		
/*		Integer[] array = new Integer[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 20);
		}*/
		
		Integer[] array = { 11, 2, 22, 1, 13,  19, 12, 17 };
		RBTree<Integer> tree = new RBTree<Integer>(array);
		System.out.println(tree);
		add(new RBTreeJPanel(tree));

	}

	@Override
	public void init() {
		super.init();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)screenSize.getWidth() / 2, (int)screenSize.getHeight() / 2);
	}
}
