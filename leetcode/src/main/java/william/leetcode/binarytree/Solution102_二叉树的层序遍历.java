package william.leetcode.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZhangShenao
 * @date 2023/8/28 10:41 AM
 * @description: https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class Solution102_二叉树的层序遍历 {
    
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
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return Collections.emptyList();
        }
        
        //借助一个队列,依次保存每层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        
        //首先将根节点入队
        queue.offer(root);
        
        //遍历队列
        while (!queue.isEmpty()) {
            //首先记录当前层节点的数量,并取出指定数量的节点
            int curLevelSize = queue.size();
            List<Integer> curLevel = new ArrayList<>(curLevelSize);
            
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                
                //依次将节点的左、右子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(curLevel);
        }
        
        return result;
    }
}
