

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * 二叉树增删改查的实现
 */
public class BinaryTree {

    static BinaryTree tree = new BinaryTree();
    //定义二叉树的根结点
    static Node root = null;
    //二叉树的结点数
    int size = 0;
    //正序遍历打印的字符串
    static String str = "";
    //定义当前结点的双亲结点
    static Node perents_node = null;

    /**
     * 添加结点
     * @param data
     */
    void add_Node(Node curr_node,int data){
        //实例化新结点
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            size++;
        }else{
            if(curr_node.data>=data){
                if(curr_node.left==null){
                    curr_node.left = newNode;
                    size++;
                    return;
                }
                curr_node = curr_node.left;
            }else{
                if(curr_node.right==null){
                    curr_node.right = newNode;
                    size++;
                    return;
                }
                curr_node = curr_node.right;
            }
            add_Node(curr_node,data);
        }
    }

    /**
     * 删除指定的结点
     */
    void delete_Node(int data){
        if(root==null){
            System.out.println("当前二叉树为空");
        }else {
            //设置parent_node为根节点,用来记录当前结点的双亲结点
            Node  parent_node = root;
            //获取当前结点
            Node curr_node = root;
            //记录当前结点是否为左节点
            boolean isLeftFalg = true;
            //记录是否删除的变量
            boolean isDelete = true;
            //判断根节点的值和传来的值是否相等、
            while (curr_node.data != data) {
                if(curr_node.left==null){
                    System.out.println("要删除的值在"+data+"该树中查找当前结点不存在");
                    isDelete = false;
                    break;
                }
                //把cur_node赋值给parent_node
                parent_node = curr_node;
                if (curr_node.data > data) {
                    //向左查找
                    curr_node = curr_node.left;
                    isLeftFalg = true;
                } else if (curr_node.data < data) {
                    //向右查找
                    curr_node = curr_node.right;
                    isLeftFalg= false;
                }
            }
            if(isDelete){
                if (parent_node != curr_node) {
                    //当前结点为叶子结点
                    if(curr_node.left==null&&curr_node.right==null){
                        if (isLeftFalg) {
                            //删除双亲的左节点
                            parent_node.left = null;
                        } else {
                            //删除双亲的右节点
                            parent_node.right = null;
                        }
                    }
                    //删除当前结点有一个子节点
                    if((curr_node.left!=null&&curr_node.right==null)||
                            (curr_node.left==null&&curr_node.right!=null)){
                        if(isLeftFalg){
                            parent_node.left = curr_node.left;
                        }else{
                            parent_node.right = curr_node.right;
                            //parent_node.right = null;
                        }
                    }
                    //删除当前结点有两个叶子结点
                    if(curr_node.left!=null&&curr_node.right!=null){
                        //Node sufNode = findNode(curr_node);
                        if(isLeftFalg){
                            parent_node.left = null;
                        }else{
                            parent_node.right = null;
                        }
                    }
                } else {
                    root = null;
                }
                System.out.print("删除值为"+data+"的结点之后的树的先序遍历结果为：");
                tree.printBinaryTree(root,2,"");
            }
        }
    }

    /**
     * 修改结点的值
     */
    void update_node(Node curr_node,int oldData,int data){
        if (curr_node!=null){
            if(curr_node.data==oldData){
                curr_node.data = data;
            }
            update_node(curr_node.left,oldData,data);
            update_node(curr_node.right,oldData,data);
        }
    }

    /**
     * 先序遍历二叉树并打印
     */
    void printBinaryTree(Node curr_node,int i,String str){
        if (curr_node!=null){
            if(i==0){
                str = curr_node.data + "left ";
            }else if(i==1){
                str = curr_node.data + "right ";
            }else{
                str = curr_node.data + " ";
            }
            System.out.print(str);
            printBinaryTree(curr_node.left,0,str);
            printBinaryTree(curr_node.right,1,str);
        }
    }

    public static void main(String[] args){
        //BinaryTree tree = new BinaryTree();
        tree.add_Node(root,45);
        tree.add_Node(root,24);
        tree.add_Node(root,53);
        tree.add_Node(root,12);
        tree.add_Node(root,34);
        tree.add_Node(root,23);
        tree.add_Node(root,3);
        tree.printBinaryTree(root,2,"");
        System.out.println();
        System.out.print("将值为24的结点修改为26：");
        tree.update_node(root,24,26);
        System.out.println();
        tree.printBinaryTree(root,2,"");
        System.out.println();
        tree.delete_Node(3);
        System.out.println();
    }
}