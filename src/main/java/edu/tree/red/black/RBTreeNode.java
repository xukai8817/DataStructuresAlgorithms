package edu.tree.red.black;

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

	public static <E> RBTreeNode<E> balanceDeletion(RBTreeNode<E> root, RBTreeNode<E> x) {
		for (RBTreeNode<E> xp, xpl, xpr;;) {
			// x为空或根
			if (x == null || x == root)
				return root;
			else if ((xp = x.parent) == null) {
				// x为根结点
				System.out.println("x=" + x.e + ",x为根结点");
				x.red = false;
				return x;
			} else if (x.red) {
				// x为红色
				return root;
			}
			// x:left-child
			else if ((xpl = xp.left) == x) {
				// x' brother is red
				if ((xpr = xp.right) != null && xpr.red) {
					xpr.red = false;
					xp.red = true;
					root = rotateLeft(root, xp);
					xpr = ((xp = x.parent) == null) ? null : xp.right;
				}

				if (xpr == null)
					x = xp;
				else {
					// x' brother is black
					RBTreeNode<E> sl = xpr.left, sr = xpr.right;
					if ((sr == null || !sr.red) && (sl == null || !sl.red)) {
						// 侄结点同时为黑结点
						xpr.red = true;
					} else {
						// 侄结点case
						// 右侄子结点为空或为黑结点,case3
						if (sr == null || !sr.red) {
							if (sl != null)
								sl.red = false;

							xpr.red = true;
							root = rotateRight(root, xpr);
							xpr = (xp = x.parent) == null ? null : xp.parent;
						}
						if (xpr != null) {
							xpr.red = (xp == null) ? false : xp.red;
							if ((sr = xpr.right) != null)
								sr.red = false;
						}
						if (xp != null) {
							xp.red = false;
							root = rotateLeft(root, xp);
						}
						x = root;
					}
				}
			}
			// x:right-child
			else {
				if (xpl != null && xpl.red) {
                    xpl.red = false;
                    xp.red = true;
                    root = rotateRight(root, xp);
                    xpl = (xp = x.parent) == null ? null : xp.left;
                }
                if (xpl == null)
                    x = xp;
                else {
                	RBTreeNode<E> sl = xpl.left, sr = xpl.right;
                    if ((sl == null || !sl.red) &&
                        (sr == null || !sr.red)) {
                        xpl.red = true;
                        x = xp;
                    }
                    else {
                        if (sl == null || !sl.red) {
                            if (sr != null)
                                sr.red = false;
                            xpl.red = true;
                            root = rotateLeft(root, xpl);
                            xpl = (xp = x.parent) == null ?
                                null : xp.left;
                        }
                        if (xpl != null) {
                            xpl.red = (xp == null) ? false : xp.red;
                            if ((sl = xpl.left) != null)
                                sl.red = false;
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateRight(root, xp);
                        }
                        x = root;
                    }
                }
			}

			return root;
		}
	}

}
