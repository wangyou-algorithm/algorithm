/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/15
 * 栈（公众号：算法之灵魂拷问）
 */
public interface Stack {

    /**
     * 获取当前栈的元素个数
     * @return 当前栈的元素个数
     */
    int size();

    /**
     * 判断当前栈是否已满
     * @return 当前栈是否已满
     */
    boolean isFull();

    /**
     * 弹栈
     * @return 出栈元素
     * @throws StackEmptyException 栈为空时抛出该异常
     */
    Object pop() throws StackEmptyException;

    /**
     * 元素入栈
     * @param element 入栈元素
     * @throws StackFullException 当栈已满时抛出该异常
     */
    void push(Object element) throws StackFullException;
}
