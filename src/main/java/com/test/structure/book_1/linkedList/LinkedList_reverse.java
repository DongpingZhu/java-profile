package com.test.structure.book_1.linkedList;

public class LinkedList_reverse {
    public static void main(String[] args) {
        Node head = new Node();// 头节点(哑结点)
        Node curr = head;
        for(int i=1;i<10;i++){ // 构造链表
            curr.next = new Node(i);
            curr = curr.next;
        }
        System.out.print("逆序前：" );
        for(Node cur = head.next;cur != null;cur = cur.next){
            System.out.print(cur.data + " ");
        }
        System.out.print("\n逆序后：" );
        reverse1(head);
        for(Node cur = head.next;cur != null;cur = cur.next){
            System.out.print(cur.data + " ");
        }
    }
    // 1.就地逆序
    private static void reverse1(Node head){ // head为哑结点（头节点）
        if(head==null || head.next==null || head.next.next==null){
            return; // 最后一个是除头节点外，只有一个节点的情况
        }
        // (1) 把首节点单独出来，断开与其后的连接，其后面的连接还未变化
        Node curr = head.next; // 暂定首节点为curr
        Node next = curr.next; // 保存curr的后继
        curr.next = null; // 断开首节点
        Node pre = curr;
        curr = next; // curr指向之前保存的next,也即后移一个
        // (2) 第一个节点已经单独出来了，并且断开了连接， 那么从第二个节点开始，依次循环指向其前驱
        while (curr.next != null){
            next = curr.next; // 先保存好后继节点
            curr.next = pre; // 改向
            pre = curr; // 指向后移
            curr = next;
        }
        curr.next = pre; // curr为最后一个
        head.next = curr; // 头节点指向这最后一个
    }
}












