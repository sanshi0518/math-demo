package com.bulain.tree;

public class PreOrderTree extends AbstractTree implements Tree {

    public void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.getLeft());
        System.out.print(node.getValue());
        traverse(node.getRight());
    }

}
