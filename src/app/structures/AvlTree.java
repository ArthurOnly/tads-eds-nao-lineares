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

    public void updateBalanceFactor(AvlTreeNode node, boolean onLeft, boolean isInsert) {
        if (node == null) { return; }
        node.setBalanceFactor(node.getBalanceFactor() + (onLeft ? 1 : -1) * (isInsert ? 1 : -1));
        
        if (node.getBalanceFactor() == 0 && isInsert) { return; }
        if (node.getBalanceFactor() != 0 && !isInsert) { return; }


        updateBalanceFactor((AvlTreeNode) node.getRootNode(), onLeft, isInsert);        
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

        this.updateBalanceFactor((AvlTreeNode) node.getRootNode(), (AvlTreeNode) node.getRootNode().getLeftChild() == node, true);
    }

    @Override
    public void print() {
        AvlTreePrinter.print(this);
    }
}
