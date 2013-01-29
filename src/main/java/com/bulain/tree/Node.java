package com.bulain.tree;

import java.io.Serializable;

public class Node implements Serializable {
    private static final long serialVersionUID = 5152480448497736799L;

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
}
