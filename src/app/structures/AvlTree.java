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

        if (node.getBalanceFactor() == 2) {
            AvlTreeNode left = (AvlTreeNode) node.getLeftChild();
            if (left.getBalanceFactor() >= 0) {
                simpleRightRotation(node);
                return;
            } else if (left.getBalanceFactor() < 0) doubleRightRotation(node);
            return;
        }

        if (node.getBalanceFactor() == -2) {
            AvlTreeNode right = (AvlTreeNode) node.getRightChild();
            if (right.getBalanceFactor() <= 0) {
                simpleLeftRotation(node);
                return;
            }
            else if (right.getBalanceFactor() > 0) {
                doubleLeftRotation(node);
            }
        }

        if (node.getBalanceFactor() == 0 && isInsert) { return; }
        if (node.getBalanceFactor() != 0 && !isInsert) { return; }

        updateBalanceFactor(node, isInsert);        
    }

    public void simpleLeftRotation(AvlTreeNode node) {
        System.out.println("Simple left");
        AvlTreeNode rotationRoot = (AvlTreeNode) node.getRootNode();
        AvlTreeNode rightRoot = (AvlTreeNode) node.getRightChild();
        AvlTreeNode rightSubtreeLeftRoot = (AvlTreeNode) rightRoot.getLeftChild();
        System.out.println(rightSubtreeLeftRoot);

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
            if (rotationRoot.getLeftChild() == node)
                rotationRoot.setLeftChild(rightRoot);
            else rotationRoot.setRightChild(rightRoot);
        }
    }

    public void doubleLeftRotation(AvlTreeNode node) {
        simpleRightRotation((AvlTreeNode) node.getRightChild());
        simpleLeftRotation(node);
    }

    public void simpleRightRotation(AvlTreeNode node) {
        AvlTreeNode rotationRoot = (AvlTreeNode) node.getRootNode();
        AvlTreeNode leftRoot = (AvlTreeNode) node.getLeftChild();
        AvlTreeNode leftSubtreeRightRoot = (AvlTreeNode) leftRoot.getRightChild();
        //System.out.println("Rotation root: "+node.getObject()+"LeftRoot:"+leftRoot.getObject()+";Right:"+leftSubtreeRightRoot.getObject());

        int newBB = node.getBalanceFactor() - 1 - max(leftRoot.getBalanceFactor(), 0);
        int newAB = leftRoot.getBalanceFactor() - 1 + min(newBB, 0);

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
            if (rotationRoot.getLeftChild() == node)
                rotationRoot.setLeftChild(leftRoot);
            else rotationRoot.setRightChild(leftRoot);
        }
    }

    public void doubleRightRotation(AvlTreeNode node) {
        simpleLeftRotation((AvlTreeNode) node.getLeftChild());
        simpleRightRotation(node);
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
    public void remove(Object value) throws RuntimeException {
        AvlTreeNode node = (AvlTreeNode) search(value);
        if (node.getObject() == null) {
            throw new RuntimeException(String.format("Elemento não existe", value));
        }

        node.setObject(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        node.setBalanceFactor(0);

        this.updateBalanceFactor(node, false);
    }

    @Override
    public void print() {
        AvlTreePrinter.print(this);
    }
}
