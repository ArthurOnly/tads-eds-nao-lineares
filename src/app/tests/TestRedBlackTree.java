package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;
import java.util.Random;

public class TestRedBlackTree implements ITest {
    public static void run() { //32
        AvlTree tree = new AvlTree(32, new GenericComparator());
        tree.print();
    }
}
