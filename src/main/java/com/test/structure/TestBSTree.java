package com.test.structure;

public class TestBSTree {

    public static void main(String[] args) {
        int[] arr = {72,37,29,55,51,80};
        BSTree bsTree = new BSTree();
        for (int a: arr) {
            bsTree.insert(a);
        }
        bsTree.midTra(bsTree.root);
        BSTree.Node treeAfterDel = bsTree.delete(bsTree.root, 55);
        System.out.println("-------");
        bsTree.midTra(treeAfterDel);







    }

}
