package edu.tree.red.black;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TreeView extends JPanel {

    private static final long serialVersionUID = -3559958872832223130L;
    private int radius = 20;
    private int vGap = 50;

    private RBTree<Integer> tree;

    public RBTree<Integer> getTree() {
        return tree;
    }

    public TreeView(RBTree<Integer> tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.getRoot() != null) {
            displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void displayTree(Graphics g, RBTreeNode<Integer> current, int x, int y, int hGap) {
        setColor(current, g);
        // 圆圈
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        // 内容
        setColor(current, g);
        g.drawString(current.e + "", x - 6, y + 4);
        g.setColor(Color.BLUE);
        if (current.left != null) {
            connectLeftChild(g, x - hGap, y + vGap, x, y);
            displayTree(g, current.left, x - hGap, y + vGap, hGap / 2);
        }

        if (current.right != null) {
            connectRightChild(g, x + hGap, y + vGap, x, y);
            displayTree(g, current.right, x + hGap, y + vGap, hGap / 2);
        }
    }

    private void setColor(RBTreeNode<Integer> root, Graphics g) {
        if (root.red) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
    }

    private void connectLeftChild(Graphics g, int i, int j, int x, int y) {
        double d = Math.sqrt(vGap * vGap + (x - i) * (x - i));
        int x1 = (int) (i + radius * (x - i) / d);
        int y1 = (int) (j - radius * vGap / d);
        int x2 = (int) (x - radius * (x - i) / d);
        int y2 = (int) (y + radius * vGap / d);
        g.drawLine(x1, y1, x2, y2);
    }

    private void connectRightChild(Graphics g, int i, int j, int x, int y) {
        double d = Math.sqrt(vGap * vGap + (x - i) * (x - i));
        int x1 = (int) (i - radius * (i - x) / d);
        int y1 = (int) (j - radius * vGap / d);
        int x2 = (int) (x + radius * (i - x) / d);
        int y2 = (int) (y + radius * vGap / d);
        g.drawLine(x1, y1, x2, y2);
    }
}
