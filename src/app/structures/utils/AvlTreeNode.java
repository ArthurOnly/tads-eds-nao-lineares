package app.structures.utils;

public class AvlTreeNode extends BinaryTreeNode {
    int balanceFactor;

    public AvlTreeNode(Object o, AvlTreeNode root) {
        super(o, root);
        this.balanceFactor = 0;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getBalanceFactor() {
        return this.balanceFactor;
    }

    // public String toString() {
    //     return String.format("Object: %s, Balance Factor: %d, Root: %s, Left: %s, Right: %s", 
    //     this.getObject(), this.getBalanceFactor(), this.getRootNode(), this.getLeftChild(), this.getRightChild());
    // }
}
