/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/15
 * 基于数组实现的栈(公众号：算法之灵魂拷问)
 */
public class ArrayStack implements Stack {

    /**
     * 栈内数据的具体存储位置
     */
    private Object[] elements;

    /**
     * 当前栈顶位置
     */
    private int currentIndex = -1;

    /**
     * 栈的容量
     * @param capacity 容量
     */
    public ArrayStack(int capacity) {
        this.elements = new Object[capacity];
    }

    /**
     * 当前栈存储的元素个数
     * @return
     */
    @Override
    public int size() {
        return currentIndex + 1;
    }

    /**
     * 判断当前栈是否已满
     * @return 是否已满
     */
    @Override
    public boolean isFull() {
        return this.size() == elements.length;
    }

    /**
     * 弹栈
     * @return 弹栈元素
     * @throws StackEmptyException 当栈为空时抛出异常
     */
    @Override
    public Object pop() throws StackEmptyException {
        if(this.size() == 0){
            throw new StackEmptyException();
        }
        return elements[currentIndex--];
    }

    /**
     * 入栈
     * @param element 入栈元素
     * @throws StackFullException 栈已满时抛出该异常
     */
    @Override
    public void push(Object element) throws StackFullException {
        if(this.isFull()){
            throw new StackFullException();
        }
        elements[++currentIndex] = element;
    }
}
