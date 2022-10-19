package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.AvlTreeNode;
import app.utils.AvlTreePrinter;
import app.utils.InternetTreePrinter;
import app.utils.NewAvlTreePrinter;

public class AvlTree extends BinarySearchTree {
    public AvlTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);

        AvlTreeNode rootNode = new AvlTreeNode(root, null);
        rootNode.setLeftChild(new AvlTreeNode(null, rootNode));
        rootNode.setRightChild(new AvlTreeNode(null, rootNode));
        super.setRoot(rootNode);
    }

    private int max(int num, int num2) {
        return num > num2 ? num : num2;
    }

    private int min(int num, int num2) {
        return num < num2 ? num : num2;
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
            if (left.getBalanceFactor() == 1) simpleRightRotation(node);
            if (left.getBalanceFactor() == -1) doubleRightRotation(node);
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
        System.out.println("SimpleLeft");

        AvlTreeNode rightRoot = (AvlTreeNode) node.getRightChild();
        AvlTreeNode rightSubtreeLeftRoot = (AvlTreeNode) rightRoot.getLeftChild();

        int newBB = node.getBalanceFactor() + 1 - min(rightRoot.getBalanceFactor(), 0);
        int newAB = rightRoot.getBalanceFactor() + 1 + max(newBB, 0);

        node.setBalanceFactor(newBB);
        rightRoot.setBalanceFactor(newAB);

        rightSubtreeLeftRoot.setRootNode(node);
        rightRoot.setLeftChild(node);
        rightRoot.setRootNode(node.getRootNode());
        node.setRootNode(rightRoot);
        node.setRightChild(rightSubtreeLeftRoot);

        if ((AvlTreeNode) this.getRoot() == node) {
            this.setRoot(rightRoot);
        } else {
            node.getRootNode().setLeftChild(rightRoot);
        }
    }

    public void doubleLeftRotation(AvlTreeNode node) {
        System.out.println("DOuble left");

        this.print();
        simpleRightRotation((AvlTreeNode) node.getRightChild());
        this.print();
        simpleLeftRotation(node);
        this.print();
    }

    public void simpleRightRotation(AvlTreeNode node) {
        System.out.println("SimpleRight");

        AvlTreeNode leftRoot = (AvlTreeNode) node.getLeftChild();
        AvlTreeNode leftSubtreeRightRoot = (AvlTreeNode) leftRoot.getRightChild();

        int newBB = node.getBalanceFactor() + 1 - min(leftRoot.getBalanceFactor(), 0);
        int newAB = leftRoot.getBalanceFactor() + 1 + max(newBB, 0);

        node.setBalanceFactor(newBB);
        leftRoot.setBalanceFactor(newAB);

        leftSubtreeRightRoot.setRootNode(node);
        leftRoot.setRightChild(node);
        leftRoot.setRootNode(node.getRootNode());
        node.setRootNode(leftRoot);
        node.setLeftChild(leftSubtreeRightRoot);

        if ((AvlTreeNode) this.getRoot() == node) {
            this.setRoot(leftRoot);
        } else {
            node.getRootNode().setRightChild(leftRoot);
        }
    }

    public void doubleRightRotation(AvlTreeNode node) {
        System.out.println("Double right");

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
        InternetTreePrinter.print(this);
    }
}
