package app.tests;

import java.util.Vector;

import app.interfaces.ITest;
import app.structures.Graph;
import app.structures.utils.Edge;
import app.structures.utils.Vertex;
import app.utils.GraphAdjacencePrinter;

public class TestGraph implements ITest {
    public static void run() {
        Graph graph = new Graph(new GraphAdjacencePrinter());
        Edge e1 = graph.addEdge(2, "e1");
        Edge e2 = graph.addEdge(3, "e2");
        Edge e3 = graph.addEdge(4, "e3");
        Edge e34 = graph.addEdge(5, "e4");

        Vertex v1 = new Vertex(1);
        graph.addVertex(e1,e2, v1);
        graph.addVertex(e1,e3, new Vertex(2));
        graph.addVertex(e3,e3, new Vertex(3));
        graph.print();
        System.out.println("----");


        // vertices de aresta
        Vector<Vertex> vertexOfEdge = graph.getVertexOfEdge(e3);
        for (Vertex vertex : vertexOfEdge) {
            System.out.println(vertex.getCost());
        }

        // se sao adjancentes
        System.out.println("ARESTA ADJACENTE: " +graph.areAdjacent(e2, e3));

        // aresta oposta
        System.out.println("ARESTA OPOSTA: " +graph.getOposite(e2, v1).getLabel());

        // testando se é completo
        System.out.println("É COMPLETO: "+graph.isComplete());
        System.out.println("É EULERIANO: "+graph.isEulerian());
        System.out.println("TEM CAMINHO EULERIANO: "+graph.hasEulerianPath());
        if (graph.hasEulerianPath()) {
            for (Edge edge : graph.getEulerianPath()) {
                System.out.print(edge.getLabel()+"->");
            }
            System.out.println("");
        }

        //CRIANDO GRAFO COMPLETO
        Graph graph2 = new Graph(new GraphAdjacencePrinter());
        Edge e4 = graph2.addEdge(2, "e4");
        Edge e5 = graph2.addEdge(3, "e5");
        Edge e6 = graph2.addEdge(4, "e6");
        graph2.addVertex(e4,e5, new Vertex(1));
        graph2.addVertex(e4,e6, new Vertex(2));
        graph2.addVertex(e5,e6, new Vertex(3));
        graph2.print();
        System.out.println("É COMPLETO: "+graph2.isComplete());
        System.out.println("É EULERIANO: "+graph2.isEulerian());
        System.out.println("TEM CAMINHO EULERIANO: "+graph2.hasEulerianPath());
        if (graph2.hasEulerianPath()) {
            for (Edge edge : graph2.getEulerianPath()) {
                System.out.print(edge.getLabel()+"->");
            }
            System.out.println("");
        }
    }
}