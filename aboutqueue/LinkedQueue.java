
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/14
 * 基于链表实现的队列(算法之灵魂拷问)
 */
public class LinkedQueue implements Queue {

    static class TreeNode{
        Object val;
        TreeNode next;
    }

    /**
     * 当前长度
     */
    private int size;

    /**
     * 队首元素
     */
    private TreeNode head;

    /**
     * 队尾元素
     */
    private TreeNode tail;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Object remove() throws QueueEmptyException {
        if(this.size == 0){
            throw new QueueEmptyException();
        }
        Object element = head.val;
        head = head.next;
        size--;
        return element;
    }

    @Override
    public void insert(Object element) throws QueueFullException {
        TreeNode treeNode = new TreeNode();
        treeNode.val = element;
        //新增第一个元素的特殊处理
        if(size == 0){
            tail = treeNode;
            head = treeNode;
        }else if(size == 1){
            //新增第二个原色的特水处理
            tail.next = treeNode;
            head.next = treeNode;
            tail = treeNode;
        }else{
            tail.next = treeNode;
            tail = treeNode;
        }
        size++;
    }
}
