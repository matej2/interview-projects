package org.example.linkedTree;

public class LinkedTreeApp implements Runnable{
    @Override
    public void run() {

        LinkedTreeNode child11 = new LinkedTreeNode(null, null, 4424F);

        LinkedTreeNode child1 = new LinkedTreeNode(child11, null, 2);
        LinkedTreeNode child2 = new LinkedTreeNode(null, null, 3.1);

        LinkedTreeNode parent = new LinkedTreeNode(child1, child2, "parent");
        System.out.println(parent);
    }
}
