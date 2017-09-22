package com.edu.tree;

import java.util.Iterator;

/**
 * @author xukai
 * @param <E>
 * @date 2017年4月16日 下午6:24:12
 *
 */
public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {

    @Override
    public void inorder() {
    }

    @Override
    public void postorder() {
    }

    @Override
    public void preorder() {
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
