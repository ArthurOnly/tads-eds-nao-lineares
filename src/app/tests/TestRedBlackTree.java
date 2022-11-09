package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.RedBlackTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;
import java.util.Random;

public class TestRedBlackTree implements ITest {
    public static void run() {

        // // insert father black
        // RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        // tree.insert(16);
        // tree.print();

        // insert case 2
        RedBlackTree tree = new RedBlackTree(15, new GenericComparator());
        tree.insert(20);
        tree.insert(10);
        tree.insert(23);
        tree.print();
    }
}
