package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
public class Solution83_删除排序链表中的重复元素 {
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

    public ListNode deleteDuplicates(ListNode head) {
        //边界条件校验
        if (head == null) {
            return head;
        }

        //遍历链表,删除重复节点
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {  //如果当前节点与后继节点的值相同,则删除后继节点
                cur.next = cur.next.next;
            } else {    //否则当前节点与后继节点的值不同,则继续遍历
                cur = cur.next;
            }
        }

        //返回原头结点
        return head;
    }
}
