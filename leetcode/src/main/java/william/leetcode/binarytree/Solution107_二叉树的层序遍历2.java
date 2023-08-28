package william.leetcode.binarytree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZhangShenao
 * @date 2023/8/28 10:33 AM
 * @description:
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution107_二叉树的层序遍历2 {
    
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
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //边界条件校验
        if (root == null) {
            return Collections.emptyList();
        }
        
        //借助一个队列,按层保存节点
        Queue<TreeNode> queue = new LinkedList<>();
        
        //使用链表保存最终结果
        LinkedList<List<Integer>> result = new LinkedList<>();
        
        //首先将根节点入队
        queue.offer(root);
        
        //遍历队列
        while (!queue.isEmpty()) {
            //首先记录当前层的节点数量
            int size = queue.size();
            List<Integer> curLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                //依次将当前层的节点出队,并按顺序将其左、右子节点依次入队
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            //保存当前层结果,插入结果链表头部
            result.addFirst(curLevel);
        }
        
        //返回结果
        return result;
    }
}
