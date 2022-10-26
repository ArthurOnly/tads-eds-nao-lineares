package app.structures.utils;

public class RedBlackNode extends BinaryTreeNode {
    boolean isBlack;

    public RedBlackNode(Object o, RedBlackNode root){
        super(o, root);
        this.isBlack = false;
    }

    public void setIsBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return this.isBlack;
    }
}
