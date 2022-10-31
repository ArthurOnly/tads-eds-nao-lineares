package app.tests;

import app.interfaces.ITest;
import app.structures.AvlTree;
import app.structures.utils.AvlTreeNode;
import app.utils.GenericComparator;
import java.util.Random;

public class TestAvlTree implements ITest {
    public static void run() { //32
        AvlTree tree = new AvlTree(32, new GenericComparator());

        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.print();
        tree.remove(32);
        tree.print();
        // tree.insert(7);
        // tree.insert(8);
        // tree.insert(9);
        // tree.print();
        
        // //test simple left
        // tree.insert(33);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(34);
        // tree.print();

        // // test simple left not root
        // tree.insert(31);
        // tree.insert(33);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(34);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(35);
        // tree.print();

        // //test simple right
        // tree.insert(31);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(30);
        // tree.print();

        // //test simple right not root
        // tree.insert(33);
        // tree.insert(31);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(30);
        // tree.insert(29);
        // tree.print();

        // // test double left
        // tree.insert(34);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(33);
        // tree.print();

        // // test double right
        // tree.insert(30);
        // tree.print();
        // System.out.println("--------------------");
        // tree.insert(31);
        // tree.print();

        // // test random
        // Random gerador = new Random();
        // for (int i = 0; i < 10; i++) {
        //     int num = gerador.nextInt(40);
        //     AvlTreeNode exists = (AvlTreeNode) tree.search(num);
        //     if (exists.getObject() == null)
        //         tree.insert(num);
        //     System.out.println("-------------------------");
        //     tree.print();
        // }

        // // Test remove
        // tree.insert(34);
        // tree.insert(35);
        // tree.insert(31);
        // tree.insert(30);
        // tree.print();
        // System.out.println("----------");
        // tree.remove(35);
        // tree.print();
    }
}
