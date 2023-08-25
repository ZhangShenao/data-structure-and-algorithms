package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date 2023/8/25 5:19 PM
 * @description: https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_反转链表 {
    
    private static class ListNode {
        
        int val;
        
        ListNode next;
        
        ListNode() {
        }
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    public ListNode reverseList(ListNode head) {
        //边界条件校验
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode next = null;
        ListNode prev = null;
        ListNode cur = head;
        
        //遍历链表逐个节点,修改指针指向
        while (cur != null) {
            //记录当前节点的next
            next = cur.next;
            
            //修改指针指向
            cur.next = prev;
            
            //节点向前移动
            prev = cur;
            cur = next;
        }
        
        //返回prev
        return prev;
    }
}
