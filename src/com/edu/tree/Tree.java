package com.edu.tree;

import java.util.Iterator;

/**
 * @author xukai
 * @date 2017年4月16日 下午6:17:40
 *
 */
public interface Tree<E extends Comparable<E>> {

    public boolean search(E e);
    
    public boolean insert(E e);
    
    public boolean delete(E e);

    /**
     * 先序遍历
     */
    public void inorder();
    
    /**
     * 后序遍历
     */
    public void postorder();
    
    /**
     * 前序遍历
     */
    public void preorder();
    
    public int getSize();
    
    public boolean isEmpty();
    
    public Iterator<E> iterator();
    
}
