package com.bulain.tree;

import org.junit.Test;

public class PostOrderTraverseTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PostOrderTraverse();
        traverse.traverse(tree);
    }

}
