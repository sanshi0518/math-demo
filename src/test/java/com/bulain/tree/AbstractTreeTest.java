package com.bulain.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractTreeTest {

    @Test
    public void testHeight() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new AbstractTree() {
            public void traverse(Node node) {
            }
        };
        int height = traverse.height(tree);
        assertEquals(4, height);
    }

}
