package java_bean.day08;

/**
 * @ClassName: BinaryTree.java
 * @Author: anpeng
 * @Date: 2024/3/4 14:04
 */
public class BinaryTree <E>{
    private TreeNode root; //二叉树的根节点
    private int total; //节点的总个数

    private class TreeNode{
        TreeNode parent; //父节点
        TreeNode left; //左子节点
        TreeNode right; //右子节点
        E data; //数据

        public TreeNode(TreeNode parent, TreeNode left, TreeNode right, E data) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }
}
