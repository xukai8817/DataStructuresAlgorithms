package edu.tree.red.black;

import com.alibaba.fastjson.JSONObject;
import edu.tree.AbstractTree;

/**
 * @author xukai 红黑树
 */
public class RBTree<E extends Comparable<E>> extends AbstractTree<E> {

    private RBTreeNode<E> root;

    private int size;

    public RBTree() {
    }

    public RBTree(E[] objects) {
        for (E e : objects) {
            if (!insert(e)) {
                System.out.println(e.toString() + "元素已存在");
            }
        }
    }

    @Override
    public boolean search(E e) {
        RBTreeNode<E> cursor = this.root;
        while (cursor != null) {
            if (e.compareTo(cursor.e) < 0) {
                cursor = cursor.left;
            } else if (e.compareTo(cursor.e) > 0) {
                cursor = cursor.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 左旋
     *
     * @param root 根结点
     * @param c 当前结点
     * @return 根结点
     */
    public <E> RBTreeNode<E> rotateLeft(RBTreeNode<E> root, RBTreeNode<E> c) {
        RBTreeNode<E> r, cp, rl;
        if (c != null && (r = c.right) != null) {
            // 1.connect c and rl
            if ((rl = c.right = r.left) != null) {
                rl.parent = c;
            }
            // 2.connect r and cp
            if ((cp = r.parent = c.parent) == null) {
                (root = r).red = false; // done if c is root
            } else if (cp.left == c) {
                cp.left = r;
            } else {
                cp.right = r;
            }
            // 3.connect c and r
            r.left = c;
            c.parent = r;
        }
        return root;
    }

    /**
     * 右旋
     *
     * @param root 根结点
     * @param c 当前结点
     * @return root 根结点
     */
    public <E> RBTreeNode<E> rotateRight(RBTreeNode<E> root, RBTreeNode<E> c) {
        RBTreeNode l, cp, lr;
        if (c != null && (l = c.left) != null) {
            // 1.connect c and lr
            if ((lr = c.left = l.right) != null) {
                lr.parent = c;
            }
            // 2.connect l and cp
            if ((cp = l.parent = c.parent) == null) {
                (root = l).red = false;
            } else if (cp.left == c) {
                cp.left = l;
            } else {
                cp.right = l;
            }
            // 3.connect c and l
            l.right = c;
            c.parent = l;
        }
        return root;
    }

    public RBTreeNode<E> find(E e) {
        RBTreeNode<E> cursor = this.root;
        while (cursor != null) {
            if (e.compareTo(cursor.e) < 0) {
                cursor = cursor.left;
            } else if (e.compareTo(cursor.e) > 0) {
                cursor = cursor.right;
            } else {
                return cursor;
            }
        }
        return null;
    }

    @Override
    public boolean insert(E e) {
        RBTreeNode<E> newNode = createRBTreeNode(e);
        RBTreeNode<E> parent = null; // 插入元素的父结点
        if (root == null) {
            root = newNode;
            root.red = false;
        } else {
            RBTreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.e) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.e) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            if (e.compareTo(parent.e) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        newNode.parent = parent;
        root = this.balanceInsertion(root, newNode);
        size++;
        return true;
    }

    @Override
    public boolean delete(E e) {
        RBTreeNode<E> p, pl, pr, replacement;
        // 1、判断节点是否存在
        if ((p = this.find(e)) == null) {
            return false;
        }
        pl = p.left;
        pr = p.right;
        // 2、判断删除节点的子节点
        if (pl != null && pr != null) {
            // 2.1、获取后继节点
            RBTreeNode<E> s = pr, sl;
            while ((sl = s.left) != null) {
                s = sl;
            }

            // 2.2、交换p和s颜色
            boolean c = s.red;
            s.red = p.red;
            p.red = c;

            // 记录
            RBTreeNode<E> sr = s.right, pp = p.parent;

            // 2.3、判断后继节点是否为直系子节点
            if (s == pr) {
                p.parent = s;
                s.right = p;
            } else {
                // p关联s父节点
                RBTreeNode<E> sp = s.parent;
                if ((p.parent = sp) != null) {
                    if (s == sp.left) {
                        sp.left = p;
                    } else {
                        sp.right = p;
                    }
                }

                // p的右子节点不为空,设置为s的右节点
                if ((s.right = pr) != null) {
                    pr.parent = s;
                }
            }
            p.left = null;

            if ((p.right = sr) != null) {
                sr.parent = p;
            }
            if ((s.left = pl) != null) {
                pl.parent = s;
            }
            if ((s.parent = pp) == null) {
                root = s;
            } else if (p == pp.left) {
                pp.left = s;
            } else {
                pp.right = s;
            }
            if (sr != null) {
                replacement = sr;
            } else {
                replacement = p;
            }
        } else if (pl != null) {
            replacement = pl;
        } else if (pr != null) {
            replacement = pr;
        } else {
            replacement = p;
        }
        if (replacement != p) {
            // 删除节点不存在子节点
            RBTreeNode<E> pp = replacement.parent = p.parent;
            if (pp == null) {
                root = replacement;
            } else if (p == pp.left) {
                pp.left = replacement;
            } else {
                pp.right = replacement;
            }
            p.left = p.right = p.parent = null;
        }
        // 3、重新着色
        if (!p.red) {
            root = this.balanceDeletion(this.root, replacement);
        }
        if (replacement == p) {  // detach
            RBTreeNode<E> pp = p.parent;
            p.parent = null;
            if (pp != null) {
                if (p == pp.left) {
                    pp.left = null;
                } else if (p == pp.right) {
                    pp.right = null;
                }
            }
        }
        size--;
        return true;
    }

    /**
     * 平衡插入后的树
     *
     * @param root 根结点
     * @param x 插入结点
     */
    public <E> RBTreeNode<E> balanceInsertion(RBTreeNode<E> root, RBTreeNode<E> x) {
        // 1.遍历结点必为红结点
        x.red = true;
        for (RBTreeNode<E> xp, xpp, xppl, xppr; ; ) {
            // 2-1.空树
            if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            }
            // 2-2.xp为黑结点 || xp为根结点
            else if (!xp.red || (xpp = xp.parent) == null) {
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

    public <E> RBTreeNode<E> balanceDeletion(RBTreeNode<E> root, RBTreeNode<E> x) {
        for (RBTreeNode<E> xp, xpl, xpr; ; ) {
            if (x == null || x == root) {
                return root;
            } else if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            } else if (x.red) {
                x.red = false;
                return root;
            } else if ((xpl = xp.left) == x) {
                if ((xpr = xp.right) != null && xpr.red) {
                    xpr.red = false;
                    xp.red = true;
                    root = rotateLeft(root, xp);
                    xpr = (xp = x.parent) == null ? null : xp.right;
                }
                if (xpr == null) {
                    x = xp;
                } else {
                    RBTreeNode<E> sl = xpr.left, sr = xpr.right;
                    if ((sr == null || !sr.red) &&
                        (sl == null || !sl.red)) {
                        xpr.red = true;
                        x = xp;
                    } else {
                        if (sr == null || !sr.red) {
                            if (sl != null) {
                                sl.red = false;
                            }
                            xpr.red = true;
                            root = rotateRight(root, xpr);
                            xpr = (xp = x.parent) == null ?
                                null : xp.right;
                        }
                        if (xpr != null) {
                            xpr.red = (xp == null) ? false : xp.red;
                            if ((sr = xpr.right) != null) {
                                sr.red = false;
                            }
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateLeft(root, xp);
                        }
                        x = root;
                    }
                }
            } else { // symmetric
                if (xpl != null && xpl.red) {
                    xpl.red = false;
                    xp.red = true;
                    root = rotateRight(root, xp);
                    xpl = (xp = x.parent) == null ? null : xp.left;
                }
                if (xpl == null) {
                    x = xp;
                } else {
                    RBTreeNode<E> sl = xpl.left, sr = xpl.right;
                    if ((sl == null || !sl.red) &&
                        (sr == null || !sr.red)) {
                        xpl.red = true;
                        x = xp;
                    } else {
                        if (sl == null || !sl.red) {
                            if (sr != null) {
                                sr.red = false;
                            }
                            xpl.red = true;
                            root = rotateLeft(root, xpl);
                            xpl = (xp = x.parent) == null ?
                                null : xp.left;
                        }
                        if (xpl != null) {
                            xpl.red = (xp == null) ? false : xp.red;
                            if ((sl = xpl.left) != null) {
                                sl.red = false;
                            }
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateRight(root, xp);
                        }
                        x = root;
                    }
                }
            }
        }
    }

    /**
     * 用以newTree为根的树替换以oldTree为根的树,即连接oldTree.parent和newTree<br>
     */
    private RBTreeNode<E> rbTransplant(RBTreeNode<E> root, RBTreeNode<E> oldTree,
        RBTreeNode<E> newTree) {
        if (newTree == null) {
            return null;
        }
        if (oldTree.parent == null) {
            this.root = newTree;
        } else if (oldTree == oldTree.parent.left) {
            oldTree.parent.left = newTree;
        } else {
            oldTree.parent.right = newTree;
        }
        newTree.parent = oldTree.parent;
        return newTree;
    }

    /**
     * 获取node的后继节点
     */
    public RBTreeNode<E> getMinNode(RBTreeNode<E> node) {
        RBTreeNode<E> minNode = node;
        if (minNode == null) {
            return null;
        }

        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    @Override
    public int getSize() {
        return size;
    }

    public RBTreeNode<E> getRoot() {
        return root;
    }

    public RBTreeNode<E> createRBTreeNode(E e) {
        return new RBTreeNode<E>(e);
    }

    public JSONObject toJSON() {
        return recusiveJSON(this.root);
    }

    private JSONObject recusiveJSON(RBTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        JSONObject object = new JSONObject();
        object.put("value", node.e);
        if (node.left != null) {
            object.put("left", recusiveJSON(node.left));
        }
        if (node.right != null) {
            object.put("right", recusiveJSON(node.right));
        }

        return object;
    }

}
