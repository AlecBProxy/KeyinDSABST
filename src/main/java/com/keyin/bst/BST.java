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

    // simple balance using sorted array rebuild
    public static BST fromSortedArray(int[] sorted) {
        BST bst = new BST();
        bst.root = buildBalanced(sorted, 0, sorted.length - 1);
        return bst;
    }

    private static TreeNode buildBalanced(int[] arr, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(arr[m]);
        node.left = buildBalanced(arr, l, m - 1);
        node.right = buildBalanced(arr, m + 1, r);
        return node;
    }
}
