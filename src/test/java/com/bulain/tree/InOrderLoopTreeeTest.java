package com.bulain.tree;

import org.junit.Test;

public class InOrderLoopTreeeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new InOrderLoopTreee();
        traverse.traverse(tree);
        System.out.println();
    }

}
