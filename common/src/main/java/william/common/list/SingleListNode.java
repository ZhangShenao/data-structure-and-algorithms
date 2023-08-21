package william.common.list;

/**
 * @author ZhangShenao
 * @date 2023/8/21 11:03 AM
 * @description: 单链表的节点
 */
public class SingleListNode<T> {
    
    /**
     * 节点值
     */
    public T value;
    
    /**
     * 指向后继节点的引用
     */
    public SingleListNode<T> next;
    
    public SingleListNode(T value, SingleListNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
