package app.tests;

import app.interfaces.ITest;
import app.structures.Graph;
import app.utils.GraphAdjacencePrinter;

public class TestGraph implements ITest {
    public static void run() {
        Graph graph = new Graph(new GraphAdjacencePrinter());
        graph.addEdge(2);
        graph.addEdge(3);
        graph.addEdge(4);
        graph.addVertex(0,1, 40);
        graph.addVertex(0,2, 2);
        graph.addVertex(2,2, 20);
        graph.print();
        //System.out.println(graph.connections().size());
    }
}