package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date 2023/8/24 9:32 AM
 * @description: https://leetcode.cn/problems/merge-two-sorted-lists/
 * <p>合并两个有序链表</p>
 */
public class Solution21 {
    
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
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //边界条件校验
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        ListNode dummy = new ListNode();    //新链表的哑头结点
        ListNode cur = dummy;
        
        //从头开始,同时遍历两个链表,选择数值较小的节点,添加到新链表末尾,并将该节点向后移动
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                cur.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            
            cur = cur.next;
        }
        
        //处理其中一条链表已经遍历完的情况
        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }
        return dummy.next;
    }
}
