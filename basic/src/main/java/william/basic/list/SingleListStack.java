package william.basic.list;

import william.common.list.SingleListNode;

/**
 * @author ZhangShenao
 * @date 2023/8/22 11:10 AM
 * @description: 使用单链表实现队列
 */
public class SingleListStack<T> {
    
    /**
     * 头指针,指向栈顶
     */
    private SingleListNode<T> head;
    
    /**
     * 记录当前栈中元素数量
     */
    private int size;
    
    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * 获取当前栈中元素数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 将元素压栈
     */
    public void push(T ele) {
        head = new SingleListNode<>(ele, head);
        ++size;
    }
    
    /**
     * 弹出栈顶元素
     */
    public T pop() {
        if (head == null) {
            return null;
        }
        SingleListNode<T> oldHead = head;
        head = oldHead.next;
        oldHead.next = null;
        --size;
        return oldHead.value;
    }
    
    /**
     * 获取栈顶元素,并不弹栈
     */
    public T top() {
        if (head == null) {
            return null;
        }
        return head.value;
    }
    
    public void printAll() {
        System.out.println("栈中所有元素为: ");
        SingleListNode<T> node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        SingleListStack<Integer> stack = new SingleListStack<>();
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }
        System.out.println("压栈10个元素后,栈长度为: " + stack.size());
        stack.printAll();
        
        System.out.println("弹栈元素: " + stack.pop());
        System.out.println("弹栈元素: " + stack.pop());
        System.out.println("弹栈元素: " + stack.pop());
        System.out.println("弹栈3个元素后,栈长度为: " + stack.size());
        stack.printAll();
        
        System.out.println("当前栈顶元素为: " + stack.top());
        System.out.println("弹栈所有元素: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}
