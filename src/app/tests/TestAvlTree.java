package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;

public class TestAvlTree implements ITest {
    public static void run() {
        AvlTree tree = new AvlTree(32, new GenericComparator());
        tree.print();
        System.out.println("--------------------");
        tree.insert(34);
        tree.print();
        System.out.println("--------------------");
        tree.insert(35);
        tree.print();
        System.out.println("--------------------");
        tree.insert(36);
        tree.print();
        System.out.println("--------------------");
        tree.insert(33);
        tree.print();
        System.out.println("--------------------");

        // tree.insert(31);
        // tree.insert(35);
        // tree.insert(33);
        // tree.insert(36);
        // tree.insert(38);
        // tree.print();

        // tree.insert(9);
        // tree.insert(8);
        // tree.insert(1);
        // tree.insert(-1);
        // tree.insert(4);
        // tree.insert(2);
        // // tree.insert(5);
        // // tree.insert(3);
    
        // tree.print();

        //tree.remove(1);

        //tree.print();
    }
}
