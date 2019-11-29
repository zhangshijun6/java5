

/**
 * 二叉树结点
 */
public class Node {
    //结点值
    int data;
    //指向左子树的指针
    Node left;
    //指向右子树的指针
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node() {
        super();
    }

    public Node(int data) {
        this.data = data;
    }
}