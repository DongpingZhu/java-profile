package com.test.structure;

public class RBTree {
    class RBNode{
        int data;
        RBNode left;
        RBNode right;
        RBNode parent;
        boolean color;
        public RBNode(int data){
            this.data = data;
            this.color = false;// false表示红，true表示黑
        }
    }
    RBNode root = null; // 红黑树根节点

    // 查询
    public static RBNode query(RBNode root ,int val){
        RBNode curr = root;
        while (curr != null){
            if(curr.data == val){
                return curr;
            }else if(val < curr.data){
                curr = curr.left;
            }else if(val > curr.data){
                curr = curr.right;
            }
        }
        return null;
    }
    // 插入

    // 删除



}
