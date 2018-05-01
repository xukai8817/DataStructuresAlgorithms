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
        // 一、查找删除节点p
        if ((p = this.find(e)) == null) {
            return false;
        }
        pl = p.left;
        pr = p.right;

        // 二、判断p的子节点
        if (pl != null && pr != null) {
            // 1、p存在两个子节点

            // 2、交换p和其后继节点s的位置和颜色

            // 2.1、获取后继节点s
            RBTreeNode<E> s = pr, sl;
            while ((sl = s.left) != null) {
                s = sl;
            }

            // 2.2、交换p和s的颜色
            boolean c = s.red;
            s.red = p.red;
            p.red = c;

            // 2.3、s是否为p的右子节点
            RBTreeNode<E> sr = s.right, pp = p.parent;
            if (s == pr) {
                // 2.3.1、s为p的右子节点，s存在为p的父节点
                // 关联s节点的右子节点
                p.parent = s;
                s.right = p;
            } else {
                // 2.3.2、s不为p的右子节点，s存在不为p的父节点
                // sp的左子节点关联p
                p.parent = s.parent;
                s.parent.left = p;
                // 关联s节点的右子节点
                s.right = pr;
                pr.parent = s;
            }

            // 2.4、s的右子节点不为空，关联p
            if ((p.right = sr) != null) {
                sr.parent = p;
            }

            // 2.5、关联s和pl,pp、清空p的左子节点指针
            s.left = pl;
            pl.parent = s;
            if ((s.parent = pp) == null) {
                root = s;
            } else if (p == pp.left) {
                pp.left = s;
            } else {
                pp.right = s;
            }
            p.left = null;

            // 2.6、s节点是否存在右子节点
            if (sr != null) {
                // 存在，使用该节点作为替换节点，步骤三中，清空p
                replacement = sr;
            } else {
                // 不存在，使用p作为替换节点
                replacement = p;
            }
        } else if (pl != null) {
            replacement = pl;
        } else if (pr != null) {
            replacement = pr;
        } else {
            // 删除节点为无子节点
            replacement = p;
        }
        // 三、替换节点不为p时，删除p
        if (replacement != p) {
            // 删除节点存在子节点时，replacement替换p
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
        // 四、重新着色
        if (!p.red) {
            root = this.balanceDeletion(this.root, replacement);
        }
        // 五、分离
        if (replacement == p) {
            // 双色节点为p时，与其父节点断开
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
                // x为左子节点
                if ((xpr = xp.right) != null && xpr.red) {
                    // x的兄弟节点存在，且是红色
                    xpr.red = false;
                    xp.red = true;
                    // 通过旋转将x的兄弟节点换成黑色节点
                    root = rotateLeft(root, xp);
                    xpr = (xp = x.parent) == null ? null : xp.right;
                }
                if (xpr == null) {
                    // 无兄弟节点，x向上传递
                    x = xp;
                } else {
                    // 兄弟节点s的子节点分析
                    RBTreeNode<E> sl = xpr.left, sr = xpr.right;
                    if ((sr == null || !sr.red) && (sl == null || !sl.red)) {
                        // case 1:s不存在子节点
                        // case 2:s存在两个黑色子节点
                        xpr.red = true;
                        x = xp;
                    } else {
                        if (sr == null || !sr.red) {
                            // s右孩子不存在或是黑色
                            // 通过旋转将s的右孩子换成红色
                            sl.red = false;
                            xpr.red = true;
                            root = rotateRight(root, xpr);
                            xpr = (xp = x.parent) == null ? null : xp.right;
                        }

                        // s的左孩子为红色，去掉额外黑色
                        if (xpr != null) {
                            xpr.red = xp.red;
                            if ((sr = xpr.right) != null) {
                                sr.red = false;
                            }
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateLeft(root, xp);
                        }
                        // 退出循环
                        x = root;
                    }
                }
            } else {
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
                            sr.red = false;
                            xpl.red = true;
                            root = rotateLeft(root, xpl);
                            xpl = (xp = x.parent) == null ?
                                null : xp.left;
                        }
                        if (xpl != null) {
                            xpl.red = xp.red;
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
