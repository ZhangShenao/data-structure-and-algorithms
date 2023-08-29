package william.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShenao
 * @date 2023/8/29 1:58 PM
 * @description:
 * https://leetcode.cn/problems/path-sum-ii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution113_路径总和2 {
    
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
     * 采用一个全局变量,保存最终的路径结果
     */
    private List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //边界条件校验
        if (root == null) {
            return result;
        }
        
        //递归
        findPath(root, targetSum, new ArrayList<>(), 0);
        
        //返回最终结果
        return result;
    }
    
    /**
     * 递归方法,查找满足指定和的路径
     */
    private void findPath(TreeNode root, int targetSum, List<Integer> prePath, int preSum) {
        //遍历到了叶子节点,需要判断该条路径是否满足路径和条件
        if (root.left == null && root.right == null) {
            if (preSum + root.val == targetSum) {    //满足条件,在结果中保存当前路径
                prePath.add(root.val);
                List<Integer> copy = new ArrayList<>(prePath);  //拷贝一份,避免影响遍历结果
                result.add(copy);
                
                //方法返回给父节点前,需要清理现场,把当前节点从前序路径中删除
                prePath.remove(prePath.size() - 1);
                return;
            }
        }
        
        //遍历到了非叶子节点,需要更新前序和与前序路径
        preSum += root.val;
        prePath.add(root.val);
        
        if (root.left != null) {
            findPath(root.left, targetSum, prePath, preSum);
        }
        if (root.right != null) {
            findPath(root.right, targetSum, prePath, preSum);
        }
        
        //方法返回给父节点前,需要清理现场,把当前节点从前序路径中删除
        prePath.remove(prePath.size() - 1);
    }
}
