package app.structures;

import java.util.Comparator;
import java.lang.RuntimeException;

import app.structures.utils.RedBlackTreeNode;
import app.utils.RedBlackTreePrinter;

public class RedBlackTree extends BinarySearchTree {
    public RedBlackTree(Object root, Comparator<Object> comparator) {
        super(root, comparator);
        this.setRoot(root);
    }

    public void setRoot(Object object) {
        RedBlackTreeNode rootNode = new RedBlackTreeNode(object, null);
        rootNode.setLeftChild(newEmptyChild(rootNode));
        rootNode.setRightChild(newEmptyChild(rootNode));
        rootNode.setIsBlack(true); // first rule
        super.setRoot(rootNode);
    }

    private RedBlackTreeNode newEmptyChild(RedBlackTreeNode root) {
        RedBlackTreeNode rbn = new RedBlackTreeNode(null, root);
        rbn.setIsBlack(true);
        return rbn;
    }

    @Override
    public void insert(Object value) throws RuntimeException {
        RedBlackTreeNode insertNode = (RedBlackTreeNode) this.search(value);
        if (insertNode.getObject() != null) throw new RuntimeException("Nó já inserido");

        insertNode.setObject(value);
        insertNode.setLeftChild(newEmptyChild(insertNode));
        insertNode.setRightChild(newEmptyChild(insertNode));
        insertNode.setIsBlack(false);

        RedBlackTreeNode fatherNode = (RedBlackTreeNode) insertNode.getRootNode();
        RedBlackTreeNode grandFatherNode = fatherNode != null ? (RedBlackTreeNode) fatherNode.getRootNode() : null;
        RedBlackTreeNode uncleNode = grandFatherNode != null ?
        grandFatherNode.getLeftChild() == fatherNode ? (RedBlackTreeNode) grandFatherNode.getRightChild() : (RedBlackTreeNode) grandFatherNode.getLeftChild()
        : null; 

        // 1 - se o pai é negro
        // 2 - se pai é rubro e avo negro e tio rubro
        // 3 - 
        if (fatherNode.isBlack()) {
            return;
        } else if (!fatherNode.isBlack() && grandFatherNode.isBlack() && !uncleNode.isBlack()) {
            fatherRedGrandFatherBlackUncleRedRebalance(insertNode, fatherNode, grandFatherNode, uncleNode);
        } else if (!fatherNode.isBlack() && grandFatherNode.isBlack() && uncleNode.isBlack()) {
            fatherRedGrandFatherBlackUncleBlackRebalance(insertNode, fatherNode, grandFatherNode, uncleNode);
        }
    }

    private void fatherRedGrandFatherBlackUncleRedRebalance(RedBlackTreeNode node, RedBlackTreeNode fatherNode, RedBlackTreeNode grandFatherNode, RedBlackTreeNode uncleNode) {
        grandFatherNode.setIsBlack(false);
        fatherNode.setIsBlack(true);
        uncleNode.setIsBlack(true);

        RedBlackTreeNode grandGrandFatherNode = (RedBlackTreeNode) grandFatherNode.getRootNode();
        if (grandGrandFatherNode != null && !grandGrandFatherNode.isBlack()) {
            
            node = grandFatherNode;
            fatherNode = (RedBlackTreeNode) node.getRootNode();
            grandFatherNode = fatherNode != null ? (RedBlackTreeNode) fatherNode.getRootNode() : null;
            uncleNode = grandFatherNode != null ?
            grandFatherNode.getLeftChild() == fatherNode ? (RedBlackTreeNode) grandFatherNode.getRightChild() : (RedBlackTreeNode) grandFatherNode.getLeftChild()
            : null;  

            fatherRedGrandFatherBlackUncleRedRebalance(node, fatherNode, grandFatherNode, uncleNode);
        } 
    }

    private void fatherRedGrandFatherBlackUncleBlackRebalance(RedBlackTreeNode node, RedBlackTreeNode fatherNode, RedBlackTreeNode grandFatherNode, RedBlackTreeNode uncleNode) {

    }

    public void simpleLeftRotation(RedBlackTreeNode node, RedBlackTreeNode father, RedBlackTreeNode grandFather) {
        
    }

    public void doubleLeftRotation(RedBlackTreeNode node) {
        simpleRightRotation((RedBlackTreeNode) node.getRightChild());
        //simpleLeftRotation(node);
    }

    public void simpleRightRotation(RedBlackTreeNode node) {
        RedBlackTreeNode father = (RedBlackTreeNode) node.getRootNode();

    }

    public void doubleRightRotation(RedBlackTreeNode node) {
        //simpleLeftRotation((RedBlackTreeNode) node.getLeftChild());
        simpleRightRotation(node);
    }

    @Override
    public void remove(Object value) throws RuntimeException {
        // remove
    }

    @Override
    public void print() {
        RedBlackTreePrinter.print(this);
    }
}
