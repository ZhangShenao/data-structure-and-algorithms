package william.basic.list;

import william.common.list.SingleListNode;
import william.common.utils.ListUtils;

/**
 * @author ZhangShenao
 * @date 2023/8/21 11:03 AM
 * @description: 单链表反转
 */
public class ReverseSingleList {
    
    public static void main(String[] args) {
        SingleListNode<Integer> head = ListUtils.generateRandomSingleList(10, 1, 100);
        ListUtils.printSingleList(head);
        System.out.println("反转单链表后");
        head = reverseSingleList(head);
        ListUtils.printSingleList(head);
    }
    
    /**
     * 反转单链表,返回反转后的链表头结点
     */
    private static SingleListNode<Integer> reverseSingleList(SingleListNode<Integer> head) {
        //边界条件校验
        if (head == null || head.next == null) {
            return head;
        }
        
        SingleListNode<Integer> prev = null;
        SingleListNode<Integer> next = null;
        SingleListNode<Integer> cur = head;
        
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }
}
