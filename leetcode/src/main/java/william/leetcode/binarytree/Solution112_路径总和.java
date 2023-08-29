package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/29 9:04 AM
 * @description:
 * https://leetcode.cn/problems/path-sum/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution112_路径总和 {
    
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
     * 使用全局变量保存查找结果
     */
    private boolean find = false;
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //边界条件校验
        if (root == null) {
            return false;
        }
        
        find = false;
        
        //递归查找
        findPathSum(root, 0, targetSum);
        
        //返回结果
        return find;
    }
    
    /**
     * 递归查找路径和,并更新全局结果
     */
    private void findPathSum(TreeNode root, int preSum, int targetSum) {
        //如果之前已经找到,则直接返回
        if (find) {
            return;
        }
        
        //遍历到了叶子节点,判断是否满足路径和
        if (root.left == null && root.right == null) {
            if (root.val + preSum == targetSum) {    //满足路径和,更新全局结果
                find = true;
                return;
            }
        }
        
        //遍历到了非叶子节点,更新前序路径和
        preSum += root.val;
        
        //递归遍历左、右子树
        if (root.left != null) {
            findPathSum(root.left, preSum, targetSum);
        }
        if (root.right != null) {
            findPathSum(root.right, preSum, targetSum);
        }
    }
}
