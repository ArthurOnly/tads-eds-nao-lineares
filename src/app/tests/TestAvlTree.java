package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;

public class TestAvlTree implements ITest {
    public static void run() {
        AvlTree tree = new AvlTree(5, new GenericComparator());
        
    
        // //tree.print();

        // //slide
        // // tree.insert(9);
        // // tree.insert(8);
        // // tree.insert(2);
        // // tree.insert(1);
        // // tree.insert(4);
        // // tree.insert(3);
        // // tree.insert(5);

        tree.insert(9);
        tree.insert(8);
        tree.insert(1);
        tree.insert(-1);
        tree.insert(4);
        tree.insert(2);
        // // tree.insert(5);
        // // tree.insert(3);
    
        // tree.print();

        //tree.remove(1);

        //tree.print();
    }
}
