package app.structures;

import java.util.Vector;

import app.structures.utils.Edge;
import app.structures.utils.Vertex;
import app.interfaces.IGraphPrinter;

public class Graph {
    Vector<Vector<Vector<Vertex>>> adjacencies;
    Vector<Edge> edges;
    IGraphPrinter printer;

    public Graph(IGraphPrinter printer) {
        this.printer = printer;
        this.edges = new Vector<Edge>();
        this.adjacencies = new Vector<Vector<Vector<Vertex>>>();
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);

        Vector<Vector<Vertex>> line = new Vector<Vector<Vertex>>();
        this.adjacencies.add(line);
        this.updateColumnsSize();
    }

    public void addEdge(float edgeCost) {
        Edge newEdge = new Edge(edgeCost);
        this.addEdge(newEdge);
    }

    public Vector<Edge> getEdges() {
        return this.edges;
    }

    public void addVertex(int x, int y, float vertexCost) {
        Vertex vertex = new Vertex(vertexCost);
        this.adjacencies.get(x).get(y).add(vertex);
        if (x != y) {
            this.adjacencies.get(y).get(x).add(vertex);
        }
    }

    private void updateColumnsSize() {
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            while (line.size() < this.adjacencies.size()) {
                 Vector<Vertex> column = new Vector<Vertex>();
                line.add(column);
            }
        }
    }

    public Vector<Vertex> endsInEdge(int edge) {
        Vector<Vertex> result = new Vector<Vertex>();
        for (Vector<Vertex> column : this.adjacencies.get(edge)) {
            for (Vertex vertex : column) {
                result.add(vertex);
            }
        }
        return result;
    }

    public boolean isAdjacent(int edgeOne, int edgeTwo) {
        boolean result = false;
        for (Vertex vertex : this.endsInEdge(edgeOne)) {
            if (this.endsInEdge(edgeTwo).contains(vertex)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // my methods
    public int getOrder(){
        return this.edges.size();
    } 

    public int degree(int edge) {
        int result = 0;
        for (Vector<Vertex> column : this.adjacencies.get(edge)){
            result += column.size();
        }
        return result;
    }

    public Vector<Vector<Vertex>> connections() {
        Vector<Vector<Vertex>> con = new Vector<Vector<Vertex>>();
        for (int i = 0; i < this.adjacencies.size(); i++){
            for (int z = this.adjacencies.get(i).size(); z < this.adjacencies.get(i).size()-i; i++) {
                con.add(this.adjacencies.get(i).get(z));
            }
        }
        return con;
    }


    public boolean isRegular(){
        boolean result = true;
        int degree = this.degree(0);
        for (int i = 1; i < this.edges.size(); i++) {
            if (this.degree(i) != degree) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isMultigraph(){
        boolean result = false;
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            for (Vector<Vertex> column : line) {
                if (column.size() > 1) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    } 

    public void print() {
        this.printer.print(this);
    }

    public Vector<Vector<Vector<Vertex>>> getAdjacencies() {
        return this.adjacencies;
    }
}