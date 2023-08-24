package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date 2023/8/23 9:54 AM
 * @description:
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * <p>K个一组翻转链表</p>
 */
public class Solution25 {
    
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
    
    /**
     * K个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        //边界条件校验
        if (head == null || k <= 0) {
            return head;
        }
        
        //先找到第一个分组
        ListNode start = head;
        ListNode end = findKGroupEnd(start, k);
        if (end == null) {
            //节点数量不足k个,无需翻转,直接返回原始头结点
            return head;
        }
        
        //新的头结点一定是end
        head = end;
        
        //先反转第一组
        reverse(start, end);
        
        //继续遍历链表后续节点,依次处理后面的组
        ListNode lastGroupEnd = start;
        while (lastGroupEnd.next != null) {
            start = lastGroupEnd.next;
            end = findKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            
            //将上一组的尾节点指向当前组的头结点
            lastGroupEnd.next = end;
            
            //继续处理下一组
            lastGroupEnd = start;
        }
        
        return head;
    }
    
    /**
     * 找到组内的尾节点end,形成[start,end]区间内的K个节点组
     */
    private ListNode findKGroupEnd(ListNode start, int k) {
        while (--k > 0 && start != null) {
            start = start.next;
        }
        return start;
    }
    
    /**
     * 在[start,end]节点区间内翻转链表
     */
    private void reverse(ListNode start, ListNode end) {
        //首先记录下一组的头结点
        end = end.next;
        
        ListNode cur = start;
        ListNode prev = null;
        ListNode next = null;
        
        while (cur != end) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        //将当前组的尾节点指向下一组的头结点
        start.next = end;
    }
}
