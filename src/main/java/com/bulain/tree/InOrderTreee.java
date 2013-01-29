package com.bulain.tree;

public class InOrderTreee extends AbstractTree implements Tree {
    public void traverse(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue());
        traverse(node.getLeft());
        traverse(node.getRight());
    }
}
