package william.leetcode.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZhangShenao
 * @date 2023/8/26 4:45 PM
 * @description:
 * https://leetcode.cn/problems/merge-k-sorted-lists/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Solution23_合并K个升序链表 {
    
    public class ListNode {
        
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
    
    public ListNode mergeKLists(ListNode[] lists) {
        //边界条件校验
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        //借助一个优先级队列(小顶堆)
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        
        //首先将所有链表的头结点都放入堆中
        for (int i = 0; i < lists.length; i++) {
            ListNode n = lists[i];
            if (n != null) {
                minHeap.offer(n);
            }
        }
        
        if (minHeap.isEmpty()) {
            return null;
        }
        
        //先从堆中取出最小节点,作为新链表的头结点
        ListNode head = minHeap.poll();
        if (head.next != null) {
            minHeap.offer(head.next);
        }
        
        //依次从堆中取出最小节点,最为新链表的下一个节点,并将最小节点的后继节点放入堆中
        ListNode cur = head;
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            cur.next = min;
            if (min.next != null) {
                minHeap.offer(min.next);
            }
            cur = cur.next;
        }
        
        return head;
    }
    
    
}
