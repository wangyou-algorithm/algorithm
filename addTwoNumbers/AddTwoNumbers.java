/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/18
 * leetcode题解——两数之和
 * 公众号（算法之灵魂探索）
 */
public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        while(listNode != null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    /**
     * 两数之和
     * @param l1 链表1
     * @param l2 链表2
     * @return 和链表
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flog = false;
        //头节点的前置节点，为了后续不判空而引入的额外节点
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while(l1 != null || l2 != null){
            //计算当前位置的和，如果l1在当前位置没有数字，则默认为0，l2同理
            //如果进位标示为true，在l1和l2的计算结果之上，再加上进位
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + (flog ? 1 : 0);
            //进位标示，每次分析结果前都标记为false
            flog = false;
            //如果和大于10，将进位标示更新为true，sum只保留个位上的数字
            if(sum >= 10){
                flog = true;
                sum %= 10;
            }
            //用当前的sum初始化一个新节点
            ListNode listNode = new ListNode(sum);
            //tmp的next指向该节点后，然后将该节点赋值给tmp
            tmp.next = listNode;
            tmp = listNode;
            //l1和l2都后移一位，已经为null的链表不操作
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        //如果最后一次运算需要进位，在结果的末尾追加1
        if(flog){
            tmp.next = new ListNode(1);
        }
        return head.next;
    }
}
