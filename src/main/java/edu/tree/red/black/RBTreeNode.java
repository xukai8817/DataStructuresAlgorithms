package edu.tree.red.black;

/**
 * @author xukai 红黑树
 */
public class RBTreeNode<E> extends Node<E> {

    RBTreeNode<E> parent;
    RBTreeNode<E> left;
    RBTreeNode<E> right;
    boolean red;

    public RBTreeNode(E e) {
        super(e);
        this.e = e;
    }

    public final RBTreeNode<E> root() {
        for (RBTreeNode<E> c = this, p; ; ) {
            if ((p = c.parent) == null) {
                return c;
            }
            c = p;
        }
    }

}
