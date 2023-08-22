package william.basic.list;

import william.common.list.SingleListNode;

/**
 * @author ZhangShenao
 * @date 2023/8/22 9:33 AM
 * @description: 使用单链表实现队列
 */
public class SingleListQueue<T> {
    
    /**
     * 头指针,指向队头
     */
    private SingleListNode<T> head;
    
    /**
     * 尾指针,指向队尾
     */
    private SingleListNode<T> tail;
    
    /**
     * 记录当前队列中元素数量
     */
    private int size;
    
    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * 获取当前队列中元素数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 元素入队
     */
    public void offer(T ele) {
        SingleListNode<T> cur = new SingleListNode<>(ele, null);
        if (tail == null) { //如果当前队列为空,则初始化队列
            head = tail = cur;
        } else { //队列不为空,在尾部入队
            tail.next = cur;
            tail = cur;
        }
        ++size;
    }
    
    /**
     * 元素出队
     */
    public T poll() {
        //处理空队列
        if (head == null) {
            return null;
        }
        
        //队列不为空,从队头出队
        SingleListNode<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        if (head == null) {  //队列已为空,则修改tail指针
            tail = null;
        }
        
        --size;
        return oldHead.value;
    }
    
    /**
     * 查看队头元素,并不出队
     */
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }
    
    public void printAll() {
        System.out.println("队列所有元素为: ");
        SingleListNode<T> node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        SingleListQueue<Integer> queue = new SingleListQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.offer(i);
        }
        System.out.println("入队10个元素后,队列长度为: " + queue.size());
        queue.printAll();
        
        System.out.println("出队元素: " + queue.poll());
        System.out.println("出队元素: " + queue.poll());
        System.out.println("出队元素: " + queue.poll());
        System.out.println("出队3个元素后,队列长度为: " + queue.size());
        queue.printAll();
        
        System.out.println("当前队头元素为: " + queue.peek());
        System.out.println("出队所有元素: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }
}
