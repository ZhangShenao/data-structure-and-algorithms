package william.common.utils;

import william.common.list.SingleListNode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/21 11:06 AM
 * @description: 链表相关的工具类
 */
public class ListUtils {
    
    /**
     * 生成随机单链条,返回链表的头结点
     *
     * @param len      链表长度
     * @param minValue 链表节点最小值
     * @param maxValue 链表节点最大值
     * @return 链表的头结点
     */
    public static SingleListNode<Integer> generateRandomSingleList(int len, int minValue, int maxValue) {
        if (len <= 0) {
            return null;
        }
        
        ThreadLocalRandom random = ThreadLocalRandom.current();
        SingleListNode<Integer> head = new SingleListNode<>(random.nextInt(minValue, maxValue), null);
        SingleListNode<Integer> cur = head;
        for (int i = 1; i < len; i++) {
            int value = random.nextInt(minValue, maxValue);
            cur.next = new SingleListNode<>(value, null);
            cur = cur.next;
        }
        
        return head;
    }
    
    public static <T> void printSingleList(SingleListNode<T> head) {
        if (head == null) {
            return;
        }
        System.out.println("单链表为:");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
