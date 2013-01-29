package com.bulain.tree;

import org.junit.Test;

public class InOrderTraverseTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new InOrderTraverse();
        traverse.traverse(tree);
    }

}
