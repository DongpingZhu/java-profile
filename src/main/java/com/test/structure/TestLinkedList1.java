package com.test.structure;

public class TestLinkedList1 {
    public static void main(String[] args) {
        LinkedList1 linkedList = new LinkedList1();
        linkedList.addFirst_dummy(1);
        linkedList.addFirst_dummy(3);
        linkedList.addFirst_dummy(2);
        linkedList.addNode(2,100);
        linkedList.print();
        linkedList.removeValueOnce(2);
        linkedList.print();


    }
}
