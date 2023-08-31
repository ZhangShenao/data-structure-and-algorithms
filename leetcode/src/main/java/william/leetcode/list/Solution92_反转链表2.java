package william.leetcode.list;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/reverse-linked-list-ii/solutions/634701/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
 */
public class Solution92_反转链表2 {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        //边界条件校验
        if (head == null || left < 0 || left >= right) {
            return head;
        }

        //Step1: 找到left的prev节点、left节点、right节点以及right.next节点,并记录下来
        ListNode dummy = new ListNode();    //借助一个哑头
        dummy.next = head;
        ListNode leftNodePrev = dummy;

        for (int i = 0; i < left - 1; i++) {
            leftNodePrev = leftNodePrev.next;
        }
        ListNode leftNode = leftNodePrev.next;


        ListNode rightNode = leftNodePrev;
        for (int i = 0; i < right - left + 1; i++) {      //leftNodePrev到rightNode的距离为: right-left+1
            rightNode = rightNode.next;
        }
        ListNode rightNodeNext = rightNode.next;


        //Step2: 切断[left,right]区间的链表与原链表的链接
        leftNodePrev.next = null;
        rightNode.next = null;

        //Step3: 将[left,right]区间内的链表反转
        ListNode reversedHead = reverse(leftNode);

        //Step4: 将反转后的链表区间连接到原链表上
        leftNodePrev.next = reversedHead;   //反转后的新头结点为reversedHead
        leftNode.next = rightNodeNext;  //反转后的新尾节点为leftNode(原始头结点)

        //Step5: 返回原头结点
        return dummy.next;
    }

    /**
     * 将以head为头的链表进行反转,返回反转后的头结点
     */
    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
