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

    public Vector<Vertex> get(int x, int y) {
        return this.adjacencies.get(x).get(y);
    }

    public int degree(int x) {
        int result = 0;
        for (Vector<Vertex> column : this.adjacencies.get(x)){
            result += column.size();
        }
        return result;
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

    public Vector<Vector<Vertex>> connections() {
        Vector<Vector<Vertex>> con = new Vector<Vector<Vertex>>();
        for (int i = 0; i < this.adjacencies.size(); i++){
            for (int z = this.adjacencies.get(i).size(); z < this.adjacencies.get(i).size()-i; i++) {
                con.add(this.adjacencies.get(i).get(z));
            }
        }
        return con;
    }

    public int getOrder(){
        return this.edges.size();
    } 

    //public boolean isMultigraph(){} // se é multigrafo

    //public boolean isComplete(){} // se é um grafo completo

    //public boolean isBipartite(){} // se é um grafo bipartido

    //public static booelan isSubgraph(Graph graphOne, Graph graphTwo){} // se um grafo é subgrafo 

    //public boolean isIsomorph(){}  // se é isomorfo

    //public boolean isRegular(){} // se é regular

    //public boolean hasClick(){} // se tem algum click

    //public Vector<Graph> clicks(){} // lista de clicks do grafo

    //public boolean isConnected(){} // se há pelo menos uma seqüência de arestas ligando cada par de vértices

    //public boolean isStrongConnected(){}

    //public boolean isEulerian(){}

    //public List getEulerianPath(){}

    //


    public void print() {
        this.printer.print(this);
    }

    public Vector<Vector<Vector<Vertex>>> getAdjacencies() {
        return this.adjacencies;
    }
}