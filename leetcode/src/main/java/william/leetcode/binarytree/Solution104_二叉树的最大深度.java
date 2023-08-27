package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/27 11:49 AM
 * @description: https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class Solution104_二叉树的最大深度 {
    
    private static class TreeNode {
        
        int val;
        
        TreeNode left;
        
        TreeNode right;
        
        TreeNode() {
        }
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public int maxDepth(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return 0;
        }
        
        //采用递归算法
        //以root为根的二叉树的深度=左、右子树二叉树深度的最大值+1
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
