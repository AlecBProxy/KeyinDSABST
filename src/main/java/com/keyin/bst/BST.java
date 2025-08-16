package com.keyin.bst;

public class BST {
    public TreeNode root;
    
    public void insert(int value) {
        root = insertRec(root, value);
    }
    
    private TreeNode insertRec(TreeNode node, int value) {
        if (node == null) return new TreeNode(value);
        if (value < node.value) node.left = insertRec(node.left, value);
        else node.right = insertRec(node.right, value);
        return node;
    }
    
    public static BST fromSortedArray(int[] sorted) {
        BST bst = new BST();
        bst.root = buildBalanced(sorted, 0, sorted.length - 1);
        return bst;
    }
    
    private static TreeNode buildBalanced(int[] arr, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        
        node.left = buildBalanced(arr, left, mid - 1);
        node.right = buildBalanced(arr, mid + 1, right);
        
        return node;
    }
}