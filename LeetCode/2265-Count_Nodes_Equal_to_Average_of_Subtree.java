/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodeCount = 0;
        calculateSubtreeData(root);
        return matchingNodeCount;
    }

    /**
     * Helper function using post-order traversal.
     * Returns an array of size 2:
     * index 0 -> sum of nodes in the current subtree
     * index 1 -> total count of nodes in the current subtree
     */
    private int[] calculateSubtreeData(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] leftData = calculateSubtreeData(node.left);
        int[] rightData = calculateSubtreeData(node.right);

        int totalSum = leftData[0] + rightData[0] + node.val;
        int totalCount = leftData[1] + rightData[1] + 1;

        // Integer division automatically rounds down to the nearest integer
        if (totalSum / totalCount == node.val) {
            matchingNodeCount++;
        }

        return new int[]{totalSum, totalCount};
    }
}
