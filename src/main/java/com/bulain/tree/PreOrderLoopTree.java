package com.bulain.tree;

import java.util.Stack;

public class PreOrderLoopTree extends AbstractTree implements Tree {

    public void traverse(Node node) {
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                System.out.print(node.getValue());
                node = node.getLeft();
            } else {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

}
