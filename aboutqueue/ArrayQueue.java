
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/13
 * 队列相关知识(公众号：算法之灵魂拷问)
 */
public class ArrayQueue implements Queue {

    private Object[] elements;

    /**
     * 队首元素位置
     */
    private int headIndex = 0;

    /**
     * 队尾元素索引
     */
    private int tailIndex = 0;

    private int size = 0;

    /**
     * 初始化数组
     * @param capacity 容量
     */
    public ArrayQueue(int capacity) {
        this.elements = new Object[capacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isFull() {
        return this.size() == elements.length;
    }

    /**
     * 队首元素出队
     * @return 队首元素
     * @throws QueueEmptyException 空队异常
     */
    @Override
    public Object remove() throws QueueEmptyException {
        if(this.size() == 0){
            throw new QueueEmptyException();
        }
        size--;
        Object element = elements[headIndex++];
        if(headIndex == elements.length){
            headIndex = 0;
        }
        return element;
    }

    /**
     * 新元素入队
     * @param element 新元素
     * @throws QueueFullException 满队异常
     */
    @Override
    public void insert(Object element) throws QueueFullException {
        if(isFull()){
            throw new QueueFullException();
        }
        size++;
        elements[tailIndex++] = element;
        if(tailIndex == elements.length){
            tailIndex = 0;
        }
    }
}
