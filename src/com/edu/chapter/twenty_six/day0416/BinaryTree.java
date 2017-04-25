package com.edu.chapter.twenty_six.day0416;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author xukai
 * @date 2017年4月16日 下午6:55:13
 *
 */
public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E>{

    /**
     * root-node
     */
    protected TreeNode<E> root;
    
    protected int size = 0;
    
    public BinaryTree() {
    }

    public BinaryTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }
    
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null; // 插入元素的父结点
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size ++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }
    
    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
    
    /**
     * 广度优先遍历
     */
    public void breadthFirstTraversal() {
        LinkedList<BinaryTree.TreeNode<E>> queue = new LinkedList<>();
        
        if (this.root == null) {
            return;
        }
        queue.add(this.root);
        
        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.removeFirst();
            System.out.println(node.element + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
    
    /**
     * 
     * @return 深度
     */
    public int height() {
        return height(this.root);
    }
    
    public int height(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
    
    public boolean isFullBinaryTree() {
        if (root == null) return false;
        return size == (Math.pow(2, this.height()) - 1);
        /**
         * 未测试
         * perfect(t) = if (t==NULL) then 0 else { 
                  let h=perfect(t.left)
                  if (h != -1 && h==perfect(t.right)) then 1+h else -1
             }
         */
    }

    /**
     * 结点类
     * @author xukai
     * @date 2017年4月18日 上午10:52:46
     * @param <E>
     *
     */
    public static class TreeNode<E extends Comparable<E>>{
        E element;
        TreeNode<E> left;
        TreeNode<E> right;
        
        public TreeNode(E e) {
            element = e;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result =
                prime * result + ((element == null) ? 0 : element.hashCode());
            result = prime * result + ((left == null) ? 0 : left.hashCode());
            result = prime * result + ((right == null) ? 0 : right.hashCode());
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TreeNode<?> other = (TreeNode<?>) obj;
            if (element == null) {
                if (other.element != null)
                    return false;
            } else if (!element.equals(other.element))
                return false;
            if (left == null) {
                if (other.left != null)
                    return false;
            } else if (!left.equals(other.left))
                return false;
            if (right == null) {
                if (other.right != null)
                    return false;
            } else if (!right.equals(other.right))
                return false;
            return true;
        }


        @Override
        public String toString() {
            return "TreeNode [element=" + element + ", left=" + left
                + ", right=" + right + "]";
        }

    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    public TreeNode<E> getRoot() {
        return root;
    }
    
    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;
        
        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) < 0) {
                current = current.right;
            } else 
                break;
        }
        
        return list;
    }
    
    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else 
                break;
        }
        
        if (current == null) {
            return false;
        }
        
        if (current.left == null) {
            // current-node not exist left-node
            if (parent == null) {
                root = current.right;
            } else {
                // connect to current.right and parent
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // current-node exist left-node
            TreeNode<E> parentOfRightMax = current;
            TreeNode<E> rightMax = current.left;   // current-node.Max(.right-node)
            while (rightMax.right != null) {
                parentOfRightMax = rightMax;
                rightMax = rightMax.right;
            }
        
            current.element = rightMax.element;
            
            if (parentOfRightMax.right == rightMax) {
                parentOfRightMax.right = rightMax.left;
            } else {
                // parentOfRightMost == current is true; 
                parentOfRightMax.left = rightMax.left;
            }
        }
        
        size --;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return inorderIterator();
    }

    public Iterator<E> inorderIterator() {
        return new InorderIterator();
    }

    class InorderIterator implements Iterator<E> {

        private ArrayList<E> list = new ArrayList<>();
        
        private int current = 0;
        
        public InorderIterator() {
            inorder();
        }
        
        private void inorder() {
            inorder(root);
        }
        
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()) 
                return true;
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }
        
        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inorder();
        }
    }
    
    public void clear() {
        root = null;
        size = 0;
    }
}
