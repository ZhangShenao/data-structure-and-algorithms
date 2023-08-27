package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/27 11:42 AM
 * @description: https://leetcode.cn/problems/symmetric-tree/
 */
public class Solution101_对称二叉树 {
    
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
    
    public boolean isSymmetric(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return true;
        }
        
        //采用递归算法,依次判断root的左、右子树是否对称
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if ((left == null) ^ (right == null)) {  //两棵树结构不同
            return false;
        }
        
        if (left == null && right == null) {
            return true;
        }
        
        //两棵子树对称的条件:两个节点值相同,且left和right的子树对称
        return (left.val == right.val) && (isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left));
    }
}
