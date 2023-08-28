package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/28 11:05 AM
 * @description: https://leetcode.cn/problems/balanced-binary-tree/
 */
public class Solution110_平衡二叉树 {
    
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
    
    
    /**
     * 二叉树节点信息
     */
    private static class TreeNodeInfo {
        
        private boolean balanced;   //以当前节点为根的二叉树是否为平衡二叉树
        
        private int height; //以当前节点为根的二叉树的高度
        
        public TreeNodeInfo(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return true;
        }
        
        //递归
        return traversal(root).balanced;
    }
    
    /**
     * 递归遍历二叉树,并保存每个节点的信息
     */
    private TreeNodeInfo traversal(TreeNode root) {
        //递归终止条件——空树满足平衡树
        if (root == null) {
            return new TreeNodeInfo(true, 0);
        }
        
        //依次遍历左、右子树
        TreeNodeInfo left = traversal(root.left);
        TreeNodeInfo right = traversal(root.right);
        
        //当前数的高度=左、右子树的最大高度+1
        int height = Math.max(left.height, right.height) + 1;
        
        //当前树满足平衡二叉树的条件:左、右子树均为平衡二叉树 && 左、右子树高度相差不超过1
        boolean balanced = left.balanced && right.balanced && Math.abs(left.height - right.height) <= 1;
        
        return new TreeNodeInfo(balanced, height);
    }
}
