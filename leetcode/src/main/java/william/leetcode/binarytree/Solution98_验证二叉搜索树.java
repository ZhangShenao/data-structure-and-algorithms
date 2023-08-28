package william.leetcode.binarytree;

/**
 * @author ZhangShenao
 * @date 2023/8/28 11:19 AM
 * @description: https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class Solution98_验证二叉搜索树 {
    
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
     * 记录节点信息
     */
    private static class TreeNodeInfo {
        
        private boolean bst;    //以当前节点为根的二叉树是否为二叉搜索树
        
        private int max;     //以当前节点为根的二叉树的最大值
        
        private int min;     //以当前节点为根的二叉树的最小值
        
        public TreeNodeInfo(boolean bst, int max, int min) {
            this.bst = bst;
            this.max = max;
            this.min = min;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return true;
        }
        
        //递归
        return recursive(root).bst;
    }
    
    /**
     * 递归遍历二叉树,并记录每个节点的信息
     */
    private TreeNodeInfo recursive(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return null;
        }
        
        //依次遍历左、右子树
        TreeNodeInfo left = recursive(root.left);
        TreeNodeInfo right = recursive(root.right);
        
        //更新当前树的最大、最小值
        int max = root.val;
        int min = root.val;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        
        //当前树满足二叉搜索树的条件:
        //1.左、右子树均为二叉搜索树
        //2.左子树的最大值 < 当前节点
        //3.右子树的最小值 > 当前节点
        boolean leftIsBst = (left == null || left.bst);
        boolean rightIsBst = (right == null || right.bst);
        boolean leftMaxLessThanRoot = (left == null || left.max < root.val);
        boolean rightMinMoreThanRoot = (right == null || right.min > root.val);
        boolean bst = (leftIsBst && rightIsBst && leftMaxLessThanRoot && rightMinMoreThanRoot);
        
        //返回当前节点信息
        return new TreeNodeInfo(bst, max, min);
    }
}
