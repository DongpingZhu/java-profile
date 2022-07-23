package com.test.structure;


import java.util.LinkedList;
import java.util.Queue;

public class BSTree {

    Node root = null; // 根节点

    // 链式存储的节点定义
    class Node{ //  定义二叉树节点类
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }
    // insert  插入还是比较简单，但容易导致退化，即退化成类似链表，所以有了红黑树的改进，使其保持平衡
    public void insert(int val){
        if(root==null){
            root = new Node(val);
            return;
        }
        Node curr = root;
        while (curr != null){
            if(val < curr.data){
                if(curr.left==null){
                    curr.left = new Node(val);
                    return;
                }else {
                    curr = curr.left;
                }
            }else {
                if(curr.right==null){
                    curr.right = new Node(val);
                    return;
                }else {
                    curr = curr.right;
                }
            }
        }
    }
    // 二叉搜索树的删除操作：使用递归
    // 如果目标节点大于当前节点值，则去右子树中删除；
    // 如果目标节点小于当前节点值，则去左子树中删除；
    // 如果目标节点就是当前节点，分为以下三种情况：
    //      其无左子：其右子顶替其位置，删除了该节点；
    //      其无右子：其左子顶替其位置，删除了该节点；
    //      其左右子节点都有：其左子树整体转移到其右子树的最左节点（到最后一层）的左子树上，然后右子树顶替其位置，由此删除了该节点。
    public Node delete(Node node ,int val){
        if(node == null){ // 如果根节点为空
            return null;
        }
        if(val == node.data){ // 要删除的节点就是当前节点
            if(node.left == null && node.right==null){ // case1：左右孩子都没有，直接删除即可(这种情况也涵盖在case2种)
                return null;
            }else if(node.left==null){ // case2.1: 只有一个孩子
                return node.right;
            }else if(node.right==null){ // case2.2: 只有一个孩子
                return node.left;
            }else { // case3: 有两个孩子
                Node curr = node.right;
                while (curr.left !=null){
                    curr = curr.left;
                }
                curr.left = node.left;
                return node.right;
            }
        }else if(val < node.data){ //要删除的节点在左子树
            node.left = delete(node.left,val); // 去左子树删
        }else if(val > node.data){ // 要删除的节点在右子树
            node.right = delete(node.right,val); // 去右子树删
        }
        return node; // 返回的根节点
    }



    // search
    public Node search(int val){
        if(root == null){
            return null;
        }
        Node curr = root;
        while (curr != null){
            if(curr.data == val){
                return curr;
            }else if(val<curr.data){
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }
        return null;
    }
    // contains
    public boolean contains(int val){
        if(root == null){
            return false;
        }else {
            Node node = this.search(val);
            if(node != null){
                return true;
            }
        }
        return false;
    }
    // 广度优先遍历
    public void scopeErgodic(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node = queue.poll();// 出队并删除，为null时不抛异常
        while (node != null){
            System.out.print(node.data + " ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
            node = queue.poll();

        }
    }
    // 先序遍历
    public void firstTra(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.data + " ");
        firstTra(node.left);
        firstTra(node.right);
    }
    // 后续遍历
    public void lastTra(Node node){
        if(node == null){
            return;
        }
        lastTra(node.left);
        lastTra(node.right);
        System.out.print(node.data + " ");
    }
    // 中序遍历
    public void midTra(Node node){
        if(node == null){
            return;
        }
        midTra(node.left);
        System.out.print(node.data + " ");
        midTra(node.right);
    }


}
