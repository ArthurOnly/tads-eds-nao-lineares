package app.tests;

import java.util.Vector;

import app.interfaces.ITest;

public class TestFindPath implements ITest {
    public static String ENTRY = "2";
    public static String EXIT = "3";
    public static String WALL = "1";
    public static String PATH = "0";

    public static void run() {

    }

    public Vector<Vector<Integer>> findPath(Vector<Vector<Integer>> graph, int x, int y) {
        Vector<Vector<Integer>> path = new Vector<Vector<Integer>>();
        Vector<Integer> visited = new Vector<Integer>();
        while (true) {
            // stop condition

        }
    }

}
