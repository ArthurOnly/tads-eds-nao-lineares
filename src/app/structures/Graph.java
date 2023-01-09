package app.structures;

import java.util.Vector;

import app.structures.utils.Vertex;
import app.structures.utils.Edge;
import app.interfaces.IGraphPrinter;

public class Graph {
    Vector<Vector<Vector<Edge>>> adjacencies;
    Vector<Vertex> vertexs;
    IGraphPrinter printer;

    public Graph(IGraphPrinter printer) {
        this.printer = printer;
        this.vertexs = new Vector<Vertex>();
        this.adjacencies = new Vector<Vector<Vector<Edge>>>();
    }

    public Vertex addVertex(Vertex vertex) {
        this.vertexs.add(vertex);

        Vector<Vector<Edge>> line = new Vector<Vector<Edge>>();
        this.adjacencies.add(line);
        this.updateColumnsSize();

        return vertex;
    }

    public Vector<Vertex> getVertexs() {
        return this.vertexs;
    }

    public void removeVertex(Vertex vertex) throws IllegalArgumentException {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        this.vertexs.remove(vertexPosition);
        this.adjacencies.remove(vertexPosition);
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            line.remove(vertexPosition);
        }
    }

    public Edge addEdge(Vertex from, Vertex to, Edge edge) {
        int x = this.vertexs.indexOf(from);
        int y = this.vertexs.indexOf(to);
        this.adjacencies.get(x).get(y).add(edge);
        if (x != y) {
            this.adjacencies.get(y).get(x).add(edge);
        }
        return edge;
    }

    public void removeEdge(Vertex from, Vertex to, Edge edge) throws IllegalArgumentException {
        int x = this.vertexs.indexOf(from);
        int y = this.vertexs.indexOf(to);
        boolean removed = this.adjacencies.get(x).get(y).remove(edge);
        if (!removed) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        if (x != y) {
            this.adjacencies.get(y).get(x).remove(edge);
        }
    }

    public Vector<Edge> getEdgesOfVertex(Vertex vertex) {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Vertice não encontrado");
        }
        Vector<Edge> result = new Vector<Edge>();
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Edge edge : line.get(vertexPosition)) {
                result.add(edge);
            }
        }
        return result;
    }

    private void updateColumnsSize() {
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            while (line.size() < this.adjacencies.size()) {
                Vector<Edge> column = new Vector<Edge>();
                line.add(column);
            }
        }
    }

    public boolean areAdjacent(Vertex edgeOne, Vertex edgeTwo) throws IllegalArgumentException {
        int edgeOnePosition = this.vertexs.indexOf(edgeOne);
        int edgeTwoPosition = this.vertexs.indexOf(edgeTwo);
        if (edgeOnePosition == -1 || edgeTwoPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        
        return this.adjacencies.get(edgeOnePosition).get(edgeTwoPosition).size() > 0;
    }

    public Vertex getOposite(Vertex vertex, Edge edge) throws IllegalArgumentException {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        Vertex result = null;
        for (int i = 0; i < this.adjacencies.size(); i++) {
            if (i != vertexPosition) {
                for (Edge v : this.adjacencies.get(i).get(vertexPosition)) {
                    if (v.equals(edge)) {
                        result = this.vertexs.get(i);
                        break;
                    }
                }
            }
        }
        return result;
    }

    // my methods
    public int getOrder(){
        return this.vertexs.size();
    } 

    public int degree(int edge) {
        int result = 0;
        for (Vector<Edge> column : this.adjacencies.get(edge)){
            result += column.size();
        }
        return result;
    }

    public boolean isRegular(){
        boolean result = true;
        int degree = this.degree(0);
        for (int i = 1; i < this.vertexs.size(); i++) {
            if (this.degree(i) != degree) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isMultigraph(){
        boolean result = false;
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Vector<Edge> column : line) {
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
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Vector<Edge> column : line) {
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
        Vector<Vertex> initial = this.vertexs;
        int parts = 0;

        while (initial.size() > 0) {
            parts++;
            Vector<Vertex> visited = new Vector<Vertex>();
            Vector<Vertex> toVisit = new Vector<Vertex>();
            toVisit.add(initial.get(0));
            while (toVisit.size() > 0) {
                Vertex vertex = toVisit.get(0);
                toVisit.remove(0);
                visited.add(vertex);
                for (Edge edge : this.getEdgesOfVertex(vertex)) {
                    Vertex oposite = this.getOposite(vertex, edge);
                    if (!visited.contains(oposite) && !toVisit.contains(oposite)) {
                        toVisit.add(oposite);
                    }
                }
            }
            initial.removeAll(visited);
        }

        return parts <= 1;
    }

    // se é euleriano
    public boolean isEulerian(){
        boolean result = true;
        for (int i = 0; i < this.vertexs.size(); i++) {
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
        for (int i = 0; i < this.vertexs.size(); i++) {
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
    public Vector<Vertex> getEulerianPath(){
        if (!this.hasEulerianPath()) {
            return null;
        }

        Vector<Vertex> result = new Vector<Vertex>();
        Vector<Vertex> edges = new Vector<Vertex>(this.vertexs);
        Vector<Vector<Vector<Edge>>> adjacencies = new Vector<Vector<Vector<Edge>>>(this.adjacencies);
        
        // como descobrir se está desconexo
        return result;
    }

    public boolean isBridge(Edge vertex) {
        boolean result = false;
        for (int i = 0; i < this.vertexs.size(); i++) {
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

    public Vector<Vector<Vector<Edge>>> getAdjacencies() {
        return this.adjacencies;
    }
}