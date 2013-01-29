package com.bulain.tree;

import org.junit.Test;

public class PreOrderLoopTreeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PreOrderLoopTree();
        traverse.traverse(tree);
        System.out.println();
    }

}
