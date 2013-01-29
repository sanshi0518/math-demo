package com.bulain.tree;

public abstract class AbstractTree implements Tree {

    public int height(Node node) {
        if (node == null) {
            return 0;
        }

        int lh = height(node.getLeft());
        int rh = height(node.getRight());

        int h = (lh > rh ? lh + 1 : rh + 1);

        return h;
    }

}