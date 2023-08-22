package william.common.list;

/**
 * @author ZhangShenao
 * @date 2023/8/21 11:03 AM
 * @description: 双链表的节点
 */
public class DoubleListNode<T> {
    
    /**
     * 节点值
     */
    private T value;
    
    /**
     * 指向前驱节点的引用
     */
    private DoubleListNode<T> prev;
    
    /**
     * 指向后继节点的引用
     */
    private DoubleListNode<T> next;
    
    public DoubleListNode(T value, DoubleListNode<T> prev, DoubleListNode<T> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public DoubleListNode<T> getPrev() {
        return prev;
    }
    
    public void setPrev(DoubleListNode<T> prev) {
        this.prev = prev;
    }
    
    public DoubleListNode<T> getNext() {
        return next;
    }
    
    public void setNext(DoubleListNode<T> next) {
        this.next = next;
    }
    
    
}
