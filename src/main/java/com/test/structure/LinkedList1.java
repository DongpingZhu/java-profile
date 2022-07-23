package com.test.structure;

import java.util.*;

public class LinkedList1 {

    class Node{
        public int data;
        public Node next;
        public Node(int data){
            this.data = data;
        }
        @Override
        public String toString(){
            return "Node(data:" + this.data + ")";

        }
    }

    Node head = null; // 头指针
    Node dummyHead = new Node(-1); // 哑结点，没有有效数据.也称头节点
    int size = 0; // 链表长度

    public void print(){
        Node currNode = head;
        while (currNode != null){
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    public int length(){
        int length =0;
        Node currNode = head;
        while (currNode != null){
            length++;
            currNode = currNode.next;
        }
        return length;
    }

    // 插入分为：头插入，尾插入，任意位置插入
    public void addFirst(int data){ // 头插法，无哑节点
        Node node = new Node(data);
        if(head == null){
            head = node;
        }else {
            node.next = head;
            head = node;
        }
    }
    public void addFirst_dummy(int data){ // 头插法，带有哑结点
        Node node = new Node(data);
        node.next = dummyHead.next;
        dummyHead.next = node;
        size++;
        head = node;
    }
    public void addNode(int index, int data){
        if(index<0 || index>this.length()){
            System.out.println("指定插入位置越界");
        }
        if(head==null){
            addFirst(data);
        }else {
            Node node = new Node(data);
            Node currNode = head;
            for(int i=0;i<index-1;i++){
                currNode = currNode.next;
            }
            node.next = currNode.next;
            currNode.next = node;
        }
    }
    public void addLast(int data){ // 尾插法：插在链表尾部
        Node node = new Node(data);
        if(Objects.isNull(head)){ //空链表
            head = node;
            return ;
        }
        Node temp = head;
        while (Objects.nonNull(temp.next)){
            temp = temp.next;
        }
        temp.next = node;
    }
    // 查找第一个值为val的节点索引
    public int getByValue(int val){
        int index = 0;
        for (Node node=head;index<this.length() -1;node=node.next) {
            if(node.data==val){
                return index;
            }
            index++;
        }
        return -1; // 未找到此val
    }
    // 查找索引为index的节点
    public int getByIndex(int index){
        if(index<0 || index>this.length()){
            System.out.println("索引越界");
            return -1;
        }
        Node currNode = head;
        for(int i=0; i<index;i++){
            currNode = currNode.next;
        }
        return currNode.data;
    }
    // 查找是否包含val
    public boolean contains(int val){
        int index = getByValue(val);
        return index != -1;
    }
    // 将index出值改为newVal
    public int set(int index, int newVal){
        if(index<0 || index>=this.length()){
            System.out.println("索引越界");
            return -1;
        }
        Node currNode = head;
        for (int i=0;i<index;i++){
            currNode=currNode.next;
        }
        int oldVal = currNode.data;
        currNode.data = newVal;
        return oldVal;
    }
    // 删除第一个值为val的元素
    public void removeValueOnce(int val){
        if(head == null){
            System.out.println("链表为控，无法删除");
            return;
        }
        if(head.data == val){
            Node currNode = head;
            head = head.next;
            currNode.next = null; // 断掉第一个节点的后续连接
            return;
        }
        if(head.data != val){
            Node currNode = head;
            while (currNode.next.data != val){
                currNode = currNode.next;
            }
            Node x = currNode.next;// 找到待删除节点
            currNode.next = x.next;
            x.next = null;
            return;
        }

    }


    // 删除指定位置的节点
    public boolean deleteNode(int index){
        if(index<1 || index>length()){
            return false;
        }
        if(index == 1){
            head = head.next;
            return true;
        }
        Node preNode = head;
        Node currNode = preNode.next;
        int i=2;
        while (Objects.nonNull(currNode)){
            if(i==index){
                preNode.next = currNode.next;
                return true;
            }
            preNode = preNode.next;
            currNode = currNode.next;
            i++;
        }
        return true;
    }



    public void distinctLink(){
        Node temp = head;
        Node pre = null;
        Map<Integer, Integer> map = new HashMap<>();
        while (Objects.nonNull(temp)){
            if(map.containsKey(temp.data)){
                pre.next = temp.next;
            }else {
                map.put(temp.data, 1);
                pre = temp;
            }
            temp = temp.next;
        }
    }
    public Node getLastNode(){
        Node temp =head;
        while (temp.next != null){
            temp = temp.next;
        }
        return temp;
    }









}
