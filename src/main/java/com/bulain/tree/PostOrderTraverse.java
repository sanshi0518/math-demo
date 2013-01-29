package com.bulain.tree;

public class PostOrderTraverse implements Tree {

    public void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.getRight());
        System.out.print(node.getValue());
        traverse(node.getLeft());
    }
}
