package com.edu.tree.red.black;

import com.alibaba.fastjson.JSONObject;
import com.edu.tree.AbstractTree;

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
			insert(e);
		}
	}

	@Override
	public boolean search(E e) {
		RBTreeNode<E> cursor = this.root;
		while (cursor != null) {
			if (e.compareTo(cursor.e) < 0)
				cursor = cursor.left;
			else if (e.compareTo(cursor.e) > 0)
				cursor = cursor.right;
			else
				return true;
		}
		return false;
	}

	public RBTreeNode<E> find(E e) {
		RBTreeNode<E> cursor = this.root;
		while (cursor != null) {
			if (e.compareTo(cursor.e) < 0)
				cursor = cursor.left;
			else if (e.compareTo(cursor.e) > 0)
				cursor = cursor.right;
			else
				return cursor;
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
		root = RBTreeNode.balanceInsertion(root, newNode);
		size++;
		return true;
	}

	@Override
	public boolean delete(E e) {
		RBTreeNode<E> x, y, z;
		
		if ((y = z = find(e)) == null)
			return false;

		boolean yRed = y.red;

		if (z.left == null) {
			x = z.right;
			rbTransplant(z, z.right);
		} else if (z.right == null) {
			x = z.left;
			rbTransplant(z, z.left);
		} else {
			y = this.getMinNode(z.right);
			yRed = y.red;
			x = y.right;
			if (y.parent == z) {
				// TODO
				if (x != null)
					x.parent = y;
			}
			else {
				rbTransplant( y, y.right);
				y.right = z.right;
				// TODO
				if (y.right != null)
					y.right.parent = y;
			}
			rbTransplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.red = z.red;
		}
		if (!yRed)
			root = RBTreeNode.balanceDeletion(root, x);
		size--;
		return true;
	}

	/**
	 * 用以newTree为根的树替换以oldTree为根的树,即连接oldTree.parent和newTree<br>
	 * @param root	根结点
	 * @param oldTree		
	 * @param newTree
	 */
	private void rbTransplant(RBTreeNode<E> oldTree, RBTreeNode<E> newTree) {
		if (oldTree.parent == null) {
			this.root = newTree;
		}
		else if (oldTree == oldTree.parent.left)
			oldTree.parent.left = newTree;
		else
			oldTree.parent.right = newTree;
		
		if (newTree != null)
			newTree.parent = oldTree.parent;
	}

	/**
	 * 获取该结点下最小结点
	 * @param node
	 * @return
	 */
	public RBTreeNode<E> getMinNode(RBTreeNode<E> node) {
		RBTreeNode<E> minNode = node;
		if (minNode == null)
			return null;

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
		if (node == null)
			return null;
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
