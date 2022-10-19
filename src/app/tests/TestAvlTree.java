package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;

public class TestAvlTree implements ITest {
    public static void run() {
        AvlTree tree = new AvlTree(32, new GenericComparator());
        
        //test simple left
        tree.insert(33);
        tree.print();
        System.out.println("--------------------");
        tree.insert(34);
        tree.print();

        // //test simple right
        // tree.insert(31);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(30);
        // tree.print();

        //test double right
        // tree.insert(34);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(33);
        // tree.print();
        //tree.print();
        // System.out.println("--------------------");

    }
}
