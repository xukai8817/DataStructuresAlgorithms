package com.edu.chapter.twenty_six.day0416;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

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
     * 栈实现中序遍历
     * <p>Title: nonRecursivePostorder</p>
     * <p>author : xukai</p>
     * <p>date : 2017年4月25日 下午10:28:36</p>
     */
    public void nonRecursiveInorder() {
    	ArrayList<TreeNode<E>> list = new ArrayList<>();
    	Stack<TreeNode<E>> stack = new Stack<>();
    	if (this.root == null) return ;
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		TreeNode<E> node = stack.peek();
    		
    		if (node.left != null && !list.contains(node.left)) {
    			stack.push(node.left);
    		} else {
    			stack.pop();
    			list.add(node);
    			if (node.right != null) {
    				stack.push(node.right);
    			}
    		}
    		
    		for (int i = 0; i < list.size(); i++)
    			System.out.print(((TreeNode<E>)list.get(i)).element + " ");
    		System.out.print("\n");
    	}
    }
    
    /**
     * 栈实现前序遍历<>
     * <p>Title: nonRecursivePreorder</p>
     * <p>author : xukai</p>
     * <p>date : 2017年4月25日 下午6:28:42</p>
     */
    public void nonRecursivePreorder() {
    	ArrayList<TreeNode<E>> list = new ArrayList<>();
    	Stack<TreeNode<E>> stack = new Stack<>();
    	if (this.root == null)	return;
    	stack.push(this.root);
    	
    	while (!stack.isEmpty()) {
    		TreeNode<E> node = stack.pop();
    		list.add(node);
    		
    		if (node.right != null	&& !list.contains(node.right)) {
    			stack.push(node.right);
    		}
    		
    		if (node.left != null	&& !list.contains(node.left)) {
    			stack.push(node.left);
    		}
    		
    		for (int i = 0; i < list.size(); i++)
    			System.out.print(((TreeNode<E>)list.get(i)).element + " ");
    		System.out.print("\n");
    	}
    }
    
    /**
     * 栈实现后序遍历
     * <p>Title: nonRecursivePostorder</p>
     * <p>author : xukai</p>
     * <p>date : 2017年4月25日 下午10:28:36</p>
     */
    public void nonRecursivePostorder() {
    	ArrayList<TreeNode<E>> list = new ArrayList<>();
    	Stack<TreeNode<E>> stack = new Stack<>();
    	if (this.root == null) return ;
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		TreeNode<E> node = stack.peek();
    		
    		if (node.left != null && !list.contains(node.left)) {
    			stack.push(node.left);
    		} else if (node.right != null && !list.contains(node.right)) {
    			stack.push(node.right);
    		} else {
    			stack.pop();
    			list.add(node);
    		}
    		
    		for (int i = 0; i < list.size(); i++)
    			System.out.print(((TreeNode<E>)list.get(i)).element + " ");
    		System.out.print("\n");
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
     * 获取叶子结点个数
     * <p>Title: getNumberOfLeaves</p>
     * <p>author : xukai</p>
     * <p>date : 2017年4月25日 下午11:06:20</p>
     * @param tree
     * @return
     */
    public int getNumberOfLeaves(TreeNode<E> tree) {
    	if (tree == null) {
    		return 0;
    	} else if (tree.left == null && tree.right == null) {
    		return 1;
    	} else {
    		return getNumberOfLeaves(tree.left) + getNumberOfLeaves(tree.right);
    	}
    }
    
    /**
     * 获取非叶子结点个数
     * <p>Title: getNumberOfNonLeaves</p>
     * <p>author : xukai</p>
     * <p>date : 2017年4月25日 下午11:08:50</p>
     * @param root
     * @return
     */
	public int getNumberOfNonLeaves(TreeNode<E> root) {
		if (root == null)
			return 0;
		if ((root.left != null) || (root.right != null)) {
			return 1 + getNumberOfNonLeaves(root.left) + getNumberOfNonLeaves(root.right);
		}
		return 0;
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
