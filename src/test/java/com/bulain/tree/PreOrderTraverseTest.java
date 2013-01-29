package com.bulain.tree;

import org.junit.Test;

public class PreOrderTraverseTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PreOrderTraverse();
        traverse.traverse(tree);
    }

}
