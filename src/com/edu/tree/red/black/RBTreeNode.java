package com.edu.tree.red.black;

/**
 * @author xukai
 * 红黑树
 * @param <K>
 * @param <V>
 */
public class RBTreeNode<E> extends Node<E>{

	RBTreeNode<E> parent;
	RBTreeNode<E> left;
	RBTreeNode<E> right;
	boolean red;

	public RBTreeNode(E e) {
		super(e);
		this.e = e;
	}

	public final RBTreeNode<E> root() {
		for (RBTreeNode<E> c = this, p;;) {
			if ((p = c.parent) == null)
				return c;
			c = p;
		}
	}

	/**
	 * 左旋
	 * @param root 	根结点
	 * @param c		当前结点
	 * @return root
	 */
	static <E> RBTreeNode<E> rotateLeft(RBTreeNode<E> root, RBTreeNode<E> c) {
		RBTreeNode<E> r, cp, rl;
		if (c != null && (r = c.right) != null) {
			// 1.connect c and rl
			if ((rl = c.right = r.left) != null)
				rl.parent = c;
			// 2.connect r and cp
			if ((cp = r.parent = c.parent) == null)
				(root = r).red = false;	// done if c is root
			else if (cp.left == c)
				cp.left = r;
			else
				cp.right = r;
			// 3.connect c and r
			r.left = c;
			c.parent = r;
		}
		return root;
	}
	
	/**
	 * 右旋
	 * @param root	根结点
	 * @param c		当前结点
	 * @return
	 */
	static <E> RBTreeNode<E> rotateRight(RBTreeNode<E> root, RBTreeNode<E> c) {
		RBTreeNode<E> l, cp, lr;
		if (c != null && (l = c.left) != null) {
			// 1.connect c and lr
			if ((lr = c.left = l.right) != null)
				lr.parent = c;
			// 2.connect l and cp
			if ((cp = l.parent = c.parent) == null)
				(root = l).red = false;
			else if (cp.right == c)
				cp.right = l;
			else
				cp.left = l;
			// 3.connnect c and l
			l.right = c;
			c.parent = l;
		}
		return root;
	}
	
	/**
	 * 平衡插入后的树
	 * @param root	根结点
	 * @param x		插入结点
	 * @return
	 */
	public static <E> RBTreeNode<E> balanceInsertion(RBTreeNode<E> root, RBTreeNode<E> x) {
		x.red = true;
		for (RBTreeNode<E> xp, xpp, xppl, xppr;;) {
			if ((xp = x.parent) == null) {
				x.red = false;
				return x;
			} else if (!xp.red || (xpp = xp.parent) == null)
				return root;
			if (xp == (xppl = xpp.left)) {
				if ((xppr = xpp.right) != null && xppr.red) {
					xppr.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				} else {
					if (x == xp.right) {
						root = rotateLeft(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = false;
						if (xpp != null) {
							xpp.red = true;
							root = rotateRight(root, xpp);
						}
					}
				}
			} else {
				if (xppl != null && xppl.red) {
					xppl.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				} else {
					if (x == xp.left) {
						root = rotateRight(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = false;
						if (xpp != null) {
							xpp.red = true;
							root = rotateLeft(root, xpp);
						}
					}
				}
			}
		}
	}

}
