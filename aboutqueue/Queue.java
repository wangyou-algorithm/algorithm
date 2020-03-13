
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/13
 * 定义队列的基本方法(公众号：算法之灵魂拷问)
 */
public interface Queue {

    /**
     * 获取当前队列的长度
     * @return 当前队列长度
     */
    int size();

    /**
     * 判断当前队列是否已满
     * @return 当前队列是否已满
     */
    boolean isFull();

    /**
     * 元素出队
     * @return 出队元素
     * @throws QueueEmptyException 队列空时跑出该异常
     */
    Object remove() throws QueueEmptyException;

    /**
     * 元素入队
     * @param element 入队元素
     * @throws QueueFullException 当队列满时抛出该异常
     */
    void insert(Object element) throws QueueFullException;
}
