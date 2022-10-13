package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.AvlTreeNode;

public class AvlTree extends BinarySearchTree {
    public AvlTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);
    }

    public void updateBalanceFactor(AvlTreeNode node, boolean onLeft, boolean isInsert) {
        
    }

    @Override
    public void insert(Object value) throws RuntimeException {
        AvlTreeNode node = (AvlTreeNode) search(value);
        if (node != null) {
            throw new RuntimeException("Elemento já existe");
        }

        node = new AvlTreeNode(value);
        node.setLeftChild(new AvlTreeNode(node));
        node.setRightChild(new AvlTreeNode(node));
        node.setBalanceFactor(0);

        super.insert(value); 
    }
}
