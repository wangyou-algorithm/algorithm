/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/16
 * 二叉树相关操作(公众号：算法之灵魂拷问)
 */
public class TreeTest {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        lastErgodic(treeNode1);
    }

    /**
     * 前序遍历
     */
    public static void preErgodic(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        System.out.print(treeNode.getVal());
        preErgodic(treeNode.getLeft());
        preErgodic(treeNode.getRight());
    }

    /**
     * 中序遍历
     */
    public static void middleErgodic(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        middleErgodic(treeNode.getLeft());
        System.out.print(treeNode.getVal());
        middleErgodic(treeNode.getRight());
    }

    /**
     * 后序遍历
     */
    public static void lastErgodic(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        lastErgodic(treeNode.getLeft());
        lastErgodic(treeNode.getRight());
        System.out.print(treeNode.getVal());
    }
}
