package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date 2023/8/24 9:05 AM
 * @description:
 * https://leetcode.cn/problems/add-two-numbers/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution2_两数相加 {
    
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
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //边界条件校验
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        int carry = 0;  //暂存当前进位
        ListNode dummy = new ListNode();    //新链表的哑头结点
        ListNode cur = dummy;
        
        //按从低位到高位的顺序,同时遍历两个链表,计算相加的结果,并保存进位
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            int curVal = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(curVal);
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }
        
        //处理其中一个链表已经遍历结束的情况
        while (l1 != null) {
            int sum = l1.val + carry;
            int curVal = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(curVal);
            l1 = l1.next;
            cur = cur.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            int curVal = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(curVal);
            l2 = l2.next;
            cur = cur.next;
        }
        
        //两个链表都遍历结束,考虑进位
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}
