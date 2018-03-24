package edu.tree.binary.search;

import edu.tree.binary.search.BinaryTree.TreeNode;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author xukai
 * @date 2017年4月17日 上午11:23:12
 */
public class TreeControl extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -3089035388076544312L;

    private BinaryTree<Integer> tree;

    private JTextField jtfKey = new JTextField(5);

    private TreeView view = new TreeView();

    private JButton jbtInsert = new JButton("Insert");

    private JButton jbtDelete = new JButton("Delete");

    public TreeControl(BinaryTree<Integer> tree) {
        this.tree = tree;
        setUI();
    }


    private void setUI() {
        this.setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter a key"));
        panel.add(jtfKey);
        panel.add(jbtInsert);
        panel.add(jbtDelete);
        add(panel, BorderLayout.SOUTH);

        jbtInsert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(jtfKey.getText());
                if (tree.search(key)) {
                    JOptionPane.showMessageDialog(null, key + " is already in the tree");
                } else {
                    tree.insert(key);
                    view.repaint();
                }
            }
        });

        jbtDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(jtfKey.getText());
                if (!tree.search(key)) {
                    JOptionPane.showMessageDialog(null, key + " is not in the tree");
                } else {
                    tree.delete(key);
                    view.repaint();
                    jtfKey.setText("");
                }
            }
        });

    }

    class TreeView extends JPanel {

        private static final long serialVersionUID = -3559958872832223130L;
        private int radius = 20;
        private int vGap = 50;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (tree.getRoot() != null) {
                displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
        }

        private void displayTree(Graphics g, TreeNode<Integer> root, int x, int y, int hGap) {
            g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.drawString(root.element + "", x - 6, y + 4);

            if (root.left != null) {
                connectLeftChild(g, x - hGap, y + vGap, x, y);
                displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
            }

            if (root.right != null) {
                connectRightChild(g, x + hGap, y + vGap, x, y);
                displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);
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

}
