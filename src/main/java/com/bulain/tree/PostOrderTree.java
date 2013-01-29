package com.bulain.tree;

public class PostOrderTree extends AbstractTree implements Tree {

    public void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.getLeft());
        traverse(node.getRight());
        System.out.print(node.getValue());
    }

}
