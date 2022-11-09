package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.RedBlackTreeNode;

public class RedBlackTree extends BinarySearchTree {
    public RedBlackTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);
        this.setRoot(root);
    }

    public void setRoot(Object object) {
        RedBlackTreeNode rootNode = new RedBlackTreeNode(object, null);
        rootNode.setLeftChild(new RedBlackTreeNode(null, rootNode));
        rootNode.setRightChild(new RedBlackTreeNode(null, rootNode));
        rootNode.setIsBlack(true); // first rule
        super.setRoot(rootNode);
    }

    @Override
    public void insert(Object value) throws RuntimeException {
        // insert
    }

    @Override
    public void remove(Object value) throws RuntimeException {
        // remove
    }

    @Override
    public void print() {
        //AvlTreePrinter.print(this);
    }
}
