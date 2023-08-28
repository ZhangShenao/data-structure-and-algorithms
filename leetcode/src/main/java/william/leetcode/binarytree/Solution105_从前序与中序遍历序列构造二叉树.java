package william.leetcode.binarytree;

import java.util.HashMap;

/**
 * @author ZhangShenao
 * @date 2023/8/27 3:50 PM
 * @description:
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution105_从前序与中序遍历序列构造二叉树 {
    
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
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //边界条件校验
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }
        
        //使用一个Hash表,维护中序数组中的节点值索引,便于查找节点
        HashMap<Integer, Integer> inOrderValIdx = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inOrderValIdx.put(inorder[i], i);
        }
        
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderValIdx);
        
    }
    
    /**
     * 递归函数 根据前序遍历结果preorder[preStart,preEnd]和中序遍历结果inorder[inStart,inEnd],构造二叉树,并返回根节点
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
            HashMap<Integer, Integer> inOrderValIdx) {
        //递归终止条件
        if (preStart > preEnd) { //空树
            return null;
        }
        
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        //区间内只要一个节点,直接返回根节点
        if (preStart == preEnd) {
            return root;
        }
        
        //在中序数组中,找到根节点所在的索引
        int rootIdx = inOrderValIdx.get(rootVal);
        
        //中序数组中左子树的范围:[inStart,rootIdx-1]
        //前序数组中左子树的范围:[preStart+1,preStart+1 + rootIdx-1-inStart]=[preStart+1,preStart+rootIdx-inStart]
        root.left = build(preorder, preStart + 1, preStart + rootIdx - inStart, inorder, inStart, rootIdx - 1,
                inOrderValIdx);
        
        //中序数组中右子树的范围:[rootIdx+1,inEnd]
        //前序数组中右子树的范围:[preStart+rootIdx-inStart+1,preEnd]
        root.right = build(preorder, preStart + rootIdx - inStart + 1, preEnd, inorder, rootIdx + 1, inEnd,
                inOrderValIdx);
        
        //返回根节点
        return root;
    }
}
