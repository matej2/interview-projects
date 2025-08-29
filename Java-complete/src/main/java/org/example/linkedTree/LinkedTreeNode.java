package org.example.linkedTree;

public class LinkedTreeNode {
    private final LinkedTreeNode one;
    private final LinkedTreeNode two;
    private final Object data;

    public LinkedTreeNode(LinkedTreeNode one, LinkedTreeNode two, Object data) {
        this.one = one;
        this.two = two;
        this.data = data;
    }

    public String toString() {
        return String.format("%s: (%s) (%s)", data, one, two);
    }

    public Integer getMaxSearchComplexity() {
        int complexity = 1;
        if (one != null) {
            complexity+=one.getMaxSearchComplexity();
        }
        if (two != null) {
            complexity+=two.getMaxSearchComplexity();
        }
        return complexity;
    }
}
