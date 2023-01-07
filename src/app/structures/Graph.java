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

    public Edge addEdge(Edge edge) {
        this.edges.add(edge);

        Vector<Vector<Vertex>> line = new Vector<Vector<Vertex>>();
        this.adjacencies.add(line);
        this.updateColumnsSize();

        return edge;
    }

    public Edge addEdge(float edgeCost, String edgeLabel) {
        Edge newEdge = new Edge(edgeCost, edgeLabel);
        return this.addEdge(newEdge);
    }

    public Vector<Edge> getEdges() {
        return this.edges;
    }

    public void removeEdge(Edge edge) throws IllegalArgumentException {
        int edgePosition = this.edges.indexOf(edge);
        if (edgePosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        this.edges.remove(edgePosition);
        this.adjacencies.remove(edgePosition);
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            line.remove(edgePosition);
        }
    }

    public Vertex addVertex(Edge from, Edge to, Vertex vertex) {
        int x = this.edges.indexOf(from);
        int y = this.edges.indexOf(to);
        this.adjacencies.get(x).get(y).add(vertex);
        if (x != y) {
            this.adjacencies.get(y).get(x).add(vertex);
        }
        return vertex;
    }

    public Vertex addVertex(int from, int to, float vertexCost) {
        Vertex newVertex = new Vertex(vertexCost);
        return this.addVertex(this.edges.get(from), this.edges.get(to), newVertex);
    } 

    public void removeVertex(Edge from, Edge to, Vertex vertex) throws IllegalArgumentException {
        int x = this.edges.indexOf(from);
        int y = this.edges.indexOf(to);
        boolean removed = this.adjacencies.get(x).get(y).remove(vertex);
        if (!removed) {
            throw new IllegalArgumentException("Vertice não encontrado");
        }
        if (x != y) {
            this.adjacencies.get(y).get(x).remove(vertex);
        }
    }

    public void removeVertex(int from, int to, Vertex vertex) {
        this.removeVertex(this.edges.get(from), this.edges.get(to), vertex);
    }

    public Vector<Vertex> getVertexOfEdge(Edge edge) {
        int edgePosition = this.edges.indexOf(edge);
        if (edgePosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        Vector<Vertex> result = new Vector<Vertex>();
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            for (Vertex vertex : line.get(edgePosition)) {
                result.add(vertex);
            }
        }
        return result;
    }

    private void updateColumnsSize() {
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            while (line.size() < this.adjacencies.size()) {
                 Vector<Vertex> column = new Vector<Vertex>();
                line.add(column);
            }
        }
    }

    public Vector<Vertex> endsInEdge(Edge edge) throws IllegalArgumentException {
        int edgePosition = this.edges.indexOf(edge);
        if (edgePosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        Vector<Vertex> result = new Vector<Vertex>();
        for (Vector<Vertex> column : this.adjacencies.get(edgePosition)) {
            for (Vertex vertex : column) {
                result.add(vertex);
            }
        }
        return result;
    }

    public boolean areAdjacent(Edge edgeOne, Edge edgeTwo) throws IllegalArgumentException {
        int edgeOnePosition = this.edges.indexOf(edgeOne);
        int edgeTwoPosition = this.edges.indexOf(edgeTwo);
        if (edgeOnePosition == -1 || edgeTwoPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        boolean result = false;
        for (Vertex vertex : this.endsInEdge(edgeOne)) {
            if (this.endsInEdge(edgeTwo).contains(vertex)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Edge getOposite(Edge edge, Vertex vertex) throws IllegalArgumentException {
        int edgePosition = this.edges.indexOf(edge);
        if (edgePosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        Edge result = null;
        for (int i = 0; i < this.adjacencies.size(); i++) {
            if (i != edgePosition) {
                for (Vertex v : this.adjacencies.get(i).get(edgePosition)) {
                    if (v.equals(vertex)) {
                        result = this.edges.get(i);
                        break;
                    }
                }
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

    public boolean isComplete(){
        boolean result = true;
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            for (Vector<Vertex> column : line) {
                int indexOfLine = this.adjacencies.indexOf(line);
                int indexOfColumn = line.indexOf(column);
                if (column.size() == 0 && indexOfLine != indexOfColumn) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // se é conexo
    public boolean isConnected(){
        boolean result = true;
        for (Vector<Vector<Vertex>> line : this.adjacencies) {
            for (Vector<Vertex> column : line) {
                if (column.size() == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // se é euleriano
    public boolean isEulerian(){
        boolean result = true;
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.degree(i) % 2 != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    // se tem um caminho euleriano
    public boolean hasEulerianPath(){
        int odd = 0;
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.degree(i) % 2 != 0) {
                odd++;
            }
            if (odd > 2) {
                return false;
            }
        }
        return true;
    }

    // GET EULERIAN PATH fleury
    public Vector<Edge> getEulerianPath(){
        if (!this.hasEulerianPath()) {
            return null;
        }

        Vector<Edge> result = new Vector<Edge>();
        Vector<Edge> edges = new Vector<Edge>(this.edges);
        Vector<Vector<Vector<Vertex>>> adjacencies = new Vector<Vector<Vector<Vertex>>>(this.adjacencies);
        
        // como descobrir se está desconexo
    }

    public boolean isBridge(Vertex vertex) {
        boolean result = false;
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.degree(i) == 1) {
                result = true;
                break;
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