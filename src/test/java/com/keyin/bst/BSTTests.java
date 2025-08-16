package com.keyin.bst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTTests {
    
    @Test
    void insertSingleNode_setsRoot() {
        BST bst = new BST();
        bst.insert(5);
        assertNotNull(bst.root);
        assertEquals(5, bst.root.value);
    }
    
    @Test
    void insertMultipleNodes_positionsLeftAndRight() {
        BST bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        assertEquals(3, bst.root.left.value);
        assertEquals(7, bst.root.right.value);
    }
    
    @Test
    void balancedBuild_fromSortedArray_createsMinimalHeight() {
        int[] sorted = new int[]{1, 2, 3, 4, 5};
        BST bst = BST.fromSortedArray(sorted);
        
        assertEquals(3, bst.root.value);
        
        assertEquals(1, bst.root.left.value);
        
        assertEquals(4, bst.root.right.value);
        
        assertEquals(2, bst.root.left.right.value); 
        assertEquals(5, bst.root.right.right.value); 
        
        assertNull(bst.root.left.left); 
        assertNull(bst.root.right.left); 
    }
}