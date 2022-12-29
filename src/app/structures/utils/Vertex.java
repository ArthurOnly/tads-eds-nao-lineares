package app.structures.utils;

public class Vertex {
    private float cost;
    
    public Vertex(float cost) {
        this.cost = cost;
    }

    public float getCost(){
        return this.cost;
    }

    public void setCost(float cost){
        this.cost = cost;
    }
}