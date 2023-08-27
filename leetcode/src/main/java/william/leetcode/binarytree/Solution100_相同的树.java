package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/27 11:32 AM
 * @description:
 * https://leetcode.cn/problems/same-tree/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution100_相同的树 {
    
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
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //边界条件校验
        if ((p == null) ^ (q == null)) { //两棵树结构不同
            return false;
        }
        
        if (p == null && q == null) {
            return true;
        }
        
        //递归进行
        //两棵树相等的条件:当前根节点的值相同,且左、右子树也相同
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
