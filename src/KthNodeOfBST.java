import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉搜索树，求树中第k小的结点
 */
// 解法一：遍历放在列表里面排序
public class KthNodeOfBST {
    //    解法二：中序遍历的第k-1个结点就是第k大的节点
    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(8);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(5);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(9);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(6);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(1);
//        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(4);
        binaryTreeNode1.leftNode = binaryTreeNode2;
        binaryTreeNode1.rightNode = binaryTreeNode3;
        binaryTreeNode2.leftNode = binaryTreeNode4;
        binaryTreeNode2.rightNode = binaryTreeNode5;
        binaryTreeNode4.leftNode = binaryTreeNode6;
//        binaryTreeNode4.rightNode = binaryTreeNode7;
        System.out.println(kthNodeOfBST(binaryTreeNode1, 4).value);
    }

    public static BinaryTreeNode kthNodeOfBST(BinaryTreeNode binaryTreeNode, int k) {
        BinaryTreeNode tempTreeNode = null;
        BinaryTreeNode rightTreeNode = null;
        List seqList = new ArrayList<BinaryTreeNode>();
        Stack nodeStack = new Stack<BinaryTreeNode>();
        while (binaryTreeNode != null) {
            nodeStack.push(binaryTreeNode);
            binaryTreeNode = binaryTreeNode.leftNode;
        }
        while (!nodeStack.isEmpty()) {
            tempTreeNode = (BinaryTreeNode) nodeStack.pop();
            seqList.add(tempTreeNode);
            if (tempTreeNode.rightNode != null) {
                rightTreeNode = tempTreeNode.rightNode;
                while (rightTreeNode != null) {
                    nodeStack.push(rightTreeNode);
                    rightTreeNode = rightTreeNode.leftNode;
                }
            }
        }
        sort((ArrayList<BinaryTreeNode>) seqList);
        return (BinaryTreeNode) seqList.get(k-1);
    }

    public static void sort(ArrayList<BinaryTreeNode> binaryTreeNodeArrayList) {
        for (int i = 0; i < binaryTreeNodeArrayList.size(); i++) {
            for (int j = i; j < binaryTreeNodeArrayList.size(); j++) {
                if (binaryTreeNodeArrayList.get(i).value > binaryTreeNodeArrayList.get(j).value) {
                    swap(binaryTreeNodeArrayList.get(i), binaryTreeNodeArrayList.get(j));
                }
            }
        }
    }

    public static void swap(BinaryTreeNode binaryTreeNode1, BinaryTreeNode binaryTreeNode2) {
        BinaryTreeNode temp = binaryTreeNode1;
        binaryTreeNode1 = binaryTreeNode2;
        binaryTreeNode2 = temp;
    }

}
