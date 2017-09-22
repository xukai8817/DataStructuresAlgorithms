package com.edu.tree.red.black;

/**
 * @author xukai 红黑树
 * @param <K>
 * @param <V>
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
		for (RBTreeNode<E> c = this, p;;) {
			if ((p = c.parent) == null)
				return c;
			c = p;
		}
	}

	/**
	 * 左旋
	 * 
	 * @param root
	 *            根结点
	 * @param c
	 *            当前结点
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
				(root = r).red = false; // done if c is root
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
	 * 
	 * @param root
	 *            根结点
	 * @param c
	 *            当前结点
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
	 * 
	 * @param root
	 *            根结点
	 * @param x
	 *            插入结点
	 * @return
	 */
	public static <E> RBTreeNode<E> balanceInsertion(RBTreeNode<E> root, RBTreeNode<E> x) {
		// 1.遍历结点必为红结点
		x.red = true;
		for (RBTreeNode<E> xp, xpp, xppl, xppr;;) {
			// 2-1.空树
			if ((xp = x.parent) == null) {
				x.red = false;
				return x;
			}
			// 2-2.xp为黑结点 || xpp = null
			else if (!xp.red || (xpp = xp.parent) == null) {
				// xp为红结点，需要判断xpp是否为空 why
				if (xp.red && xp.parent == null)
					System.out.println("xp.red && xpp is null, xp is root");
				return root;
			}
			// 2-3-1.xp is left-child
			// case1: a -> b
			if (xp == (xppl = xpp.left)) {
				// 2-3-1-1.x uncle is red
				if ((xppr = xpp.right) != null && xppr.red) {
					xppr.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				}
				// 2-3-1-2.x uncle is black
				else {
					// x is right-child
					// case2: b -> c
					if (x == xp.right) {
						root = rotateLeft(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					// x is left-child
					// case3: c -> d
					if (xp != null) {
						xp.red = false;
						if (xpp != null) {
							xpp.red = true;
							root = rotateRight(root, xpp);
						}
					}
				}
			}
			// 2-3-2.xp is right-child
			else {
				// 2-3-2-1.x uncle is red
				if ((xppl = xpp.left) != null && xppl.red) {
					xppl.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				}
				// 2-3-2-2.x uncle is black
				else {
					// x is left-child
					if (x == xp.left) {
						root = rotateRight(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					// x is right-child
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
