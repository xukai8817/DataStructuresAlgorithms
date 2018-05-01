package edu.tree.red.black;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;

/**
 * @author xukai
 * @date 2017年4月17日 下午12:57:09
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
        // 26, 17, 41, 14, 21, 30, 47, 10, 16, 19, 23, 28, 38, 7, 12, 15, 20, 35, 39, 3 delete 7
        Integer[] array =
            {26, 17, 41, 14, 21, 30, 47, 10, 16, 19, 23, 28, 38, 7, 12, 15, 20, 35,39, 3};
        RBTree<Integer> tree = new RBTree<Integer>(array);
        System.out.println(tree);
        add(new RBTreeJPanel(new TreeView(tree)));

    }

    @Override
    public void init() {
        super.init();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
    }
}
