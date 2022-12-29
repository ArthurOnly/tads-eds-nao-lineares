package app.utils;

import java.util.Vector;

import app.interfaces.IGraphPrinter;
import app.structures.Graph;
import app.structures.utils.Edge;
import app.structures.utils.Vertex;

public class GraphAdjacencePrinter implements IGraphPrinter {
    public GraphAdjacencePrinter() {
    }

    public void print(Graph graph) {
        if (graph.getAdjacencies() == null) {
            System.out.println("Grafo vazio");
            return;
        }
        int index = 0;
        for (Edge edge : graph.getEdges()) {
            System.out.print(String.format("%10s", "v"+index+":"+edge.getCost()));
            index++;
        }
        System.out.println();
        for (Vector<Vector<Vertex>> column : graph.getAdjacencies()) {
            for (Vector<Vertex> line : column) {
                String lineString = "";
                lineString += "[";
                for (Vertex vertex : line) {
                    lineString += vertex.getCost() + ",";
                }
                lineString += "]";
                System.out.print(String.format("%10s", lineString));
            }
            System.out.println();
        }
    }
}