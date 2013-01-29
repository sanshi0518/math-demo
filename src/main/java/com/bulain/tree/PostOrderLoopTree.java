package com.bulain.tree;

import java.util.Stack;

public class PostOrderLoopTree extends AbstractTree implements Tree {

    public void traverse(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        Node prev = null;
        Node curr = null;
        while (!stack.isEmpty()) {
            curr = stack.peek();
            if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                } else if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                }
            } else if (curr.getLeft() == prev) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                }
            } else {
                System.out.print(curr.getValue());
                stack.pop();
            }
            prev = curr;
        }
    }

}
