/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/15
 * 基于链表实现的栈（公众号：算法之灵魂拷问）
 */
public class LinkedStack implements Stack {

    static class TreeNode{
        Object val;
        TreeNode next;
    }

    /**
     * 当前队首节点（栈顶元素）
     */
    private TreeNode head;

    /**
     * 当前栈存储的元素个数
     */
    private int size;

    /**
     * 当前栈存储的元素个数
     * @return 当前栈存储的元素个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断当前栈是否已满
     * @return 是否已满
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * 弹栈
     * @return 栈顶元素
     * @throws StackEmptyException 栈空时抛出异常
     */
    @Override
    public Object pop() throws StackEmptyException {
        if(this.size() == 0){
            throw new StackEmptyException();
        }
        Object result = head.val;
        head = head.next;
        size--;
        return result;
    }

    /**
     * 元素入栈
     * @param element 入栈元素
     * @throws StackFullException 栈满时抛出异常
     */
    @Override
    public void push(Object element) throws StackFullException {
        TreeNode treeNode = new TreeNode();
        treeNode.val = element;
        treeNode.next = head;
        head = treeNode;
        size++;
    }
}
