
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/15
 * 二叉树节点定义(公众号：算法之灵魂拷问)
 */
public class TreeNode {

    /**
     * 节点存储的值
     */
    int val;
    /**
     * 左子树
     */
    TreeNode left;
    /**
     * 右子树
     */
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
