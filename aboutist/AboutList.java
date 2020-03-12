
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/12
 * 链表相关知识(公众号：算法之灵魂拷问)
 */
public class AboutList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 遍历链表
     * @param head
     */
    public static void traversalList(ListNode head){
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 在第n个位置新增节点
     * @param head 头节点
     * @param newNode 要新增的节点
     * @param n 位置
     */
    public static ListNode insertNode(ListNode head, ListNode newNode, int n){
        if(n == 0){
            newNode.next = head;
            return newNode;
        }
        //n节点的前置节点，即第n-1个节点
        ListNode preNode = head;
        for (int i = 0; i < n - 1; i++) {
            if(preNode == null){
                break;
            }
            preNode = preNode.next;
        }
        //n的前置为null，新增失败
        if(preNode == null){
            return head;
        }
        newNode.next = preNode.next;
        preNode.next = newNode;
        return head;
    }

    /**
     * 删除第n个位置上的节点
     * @param head 头节点
     * @param n 位置
     */
    public static ListNode deleteNode(ListNode head, int n){
        //链表为null，不操作
        if(head == null){
            return null;
        }
        //如果移除第0个位置的节点，即移除头节点，只需将头节点后移一位
        if(n == 0){
            return head.next;
        }
        //n节点的前置节点，即第n-1个节点
        ListNode preNode = head;
        for (int i = 0; i < n - 1; i++) {
            if(preNode == null){
                break;
            }
            preNode = preNode.next;
        }
        //如果删除的位置超过链表长度，不删除，直接返回原链表
        if(preNode == null || preNode.next == null){
            return head;
        }
        //将n的前置节点的next改为n的后置节点
        preNode.next = preNode.next.next;
        return head;
    }

    /**
     * 判断链表有环
     * @return 是否有环
     */
    private static boolean cycle(ListNode head){
        if(head == null){
            return false;
        }
        ListNode aNode = head;
        ListNode bNode = head;
        while(bNode.next != null && bNode.next.next != null){
            if(bNode.next.next == aNode.next){
                return true;
            }
            bNode = bNode.next.next;
            aNode = aNode.next;
        }
        return false;
    }

    /**
     * 反转链表
     * @param head 当前链表头节点
     * @return 反转后的链表
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode result = null;
        do{
            ListNode tempNode = head;
            head = head.next;
            tempNode.next = result;
            result = tempNode;
        }while(head != null);
        return result;
    }
}
