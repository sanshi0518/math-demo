package com.bulain.tree;

public class TreeBuilder {
    public static Node buildTree() {
        Node leaf1 = new Node(1);
        Node leaf2 = new Node(2);
        Node leaf3 = new Node(3);
        Node leaf4 = new Node(4);
        Node leaf5 = new Node(5);
        Node leaf6 = new Node(6);
        Node leaf7 = new Node(7);
        Node leaf8 = new Node(8);

        leaf4.setLeft(leaf2);
        leaf4.setRight(leaf6);

        leaf2.setLeft(leaf1);
        leaf2.setRight(leaf3);

        leaf6.setLeft(leaf5);
        leaf6.setRight(leaf7);
        
        leaf7.setRight(leaf8);

        return leaf4;
    }
}
