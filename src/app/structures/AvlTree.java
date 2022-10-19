package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.AvlTreeNode;
import app.utils.AvlTreePrinter;

public class AvlTree extends BinarySearchTree {
    public AvlTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);

        AvlTreeNode rootNode = new AvlTreeNode(root, null);
        rootNode.setLeftChild(new AvlTreeNode(null, rootNode));
        rootNode.setRightChild(new AvlTreeNode(null, rootNode));
        super.setRoot(rootNode);
    }

    public void updateBalanceFactor(AvlTreeNode node, boolean isInsert) {
        AvlTreeNode prev = node;
        node = (AvlTreeNode) node.getRootNode();
        if (node == null) return;
        boolean onLeft = node.getLeftChild() == prev;
        
        node.setBalanceFactor(node.getBalanceFactor() + (onLeft ? 1 : -1) * (isInsert ? 1 : -1));
        
        if (node.getBalanceFactor() == 0 && isInsert) { return; }
        if (node.getBalanceFactor() != 0 && !isInsert) { return; }

        if (node.getBalanceFactor() == 2) {
            AvlTreeNode left = (AvlTreeNode) node.getLeftChild();
            if (left.getBalanceFactor() == -1) simpleRightRotation(node);
            if (left.getBalanceFactor() == 1) doubleRightRotation(node);
            return;
        }

        if (node.getBalanceFactor() == -2) {
            AvlTreeNode right = (AvlTreeNode) node.getRightChild();
            if (right.getBalanceFactor() == -1) simpleLeftRotation(node);
            if (right.getBalanceFactor() == 1) doubleLeftRotation(node);
            return;
        }

        updateBalanceFactor(node, isInsert);        
    }

    public void simpleLeftRotation(AvlTreeNode node) {
        AvlTreeNode rightRoot = (AvlTreeNode) node.getRightChild();
        AvlTreeNode rightSubtreeLeftRoot = (AvlTreeNode) rightRoot.getLeftChild();

        rightSubtreeLeftRoot.setRootNode(node);
        rightRoot.setLeftChild(node);
        node.setRootNode(rightRoot);
        node.setRightChild(rightSubtreeLeftRoot);

        if ((AvlTreeNode) this.getRoot() == node) this.setRoot(rightRoot);
    }

    public void doubleLeftRotation(AvlTreeNode node) {

    }

    public void simpleRightRotation(AvlTreeNode node) {

    }

    public void doubleRightRotation(AvlTreeNode node) {

    }

    @Override
    public void insert(Object value) throws RuntimeException {
        AvlTreeNode node = (AvlTreeNode) search(value);
        if (node.getObject() == value) {
            throw new RuntimeException(String.format("Elemento %s ja existe", value));
        }

        node.setObject(value);
        node.setLeftChild(new AvlTreeNode(null, node));
        node.setRightChild(new AvlTreeNode(null, node));
        node.setBalanceFactor(0);

        this.updateBalanceFactor(node, true);
    }

    @Override
    public void print() {
        AvlTreePrinter.print(this);
    }
}
