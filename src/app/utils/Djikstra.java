package app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.*;
import app.utils.Pair;

public class Djikstra {

    public static class Pair<T, U> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class Result {
        public int distance;
        public List<Pair<Integer, Integer>> path;
    }

    static class Node {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public static Result dijkstra(int mat[][], int ROW, int COL, int srcX, int srcY) {
        List<Node> exits = new ArrayList<>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (mat[i][j] == 3) {
                    exits.add(new Node(i, j, 0));
                }
            }
        }

        Result result = new Result();
        result.distance = Integer.MAX_VALUE;
        result.path = new ArrayList<>();

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        boolean[][] visited = new boolean[ROW][COL];
        int[][] dist = new int[ROW][COL];
        List<Pair<Integer, Integer>> path[][] = new ArrayList[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                path[i][j] = new ArrayList<>();
            }
        }
    
        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};
    
        pq.add(new Node(srcX, srcY, 0));
        dist[srcX][srcY] = 0;
        path[srcX][srcY].add(new Pair<>(srcX, srcY));
    
        while (!pq.isEmpty()) {
            Node node = pq.poll();
    
            int i = node.x;
            int j = node.y;
            visited[i][j] = true;
    
            for (int k = 0; k < 4; k++) {
                if (isSafe(mat, i + rowNum[k], j + colNum[k], ROW, COL) && !visited[i + rowNum[k]][j + colNum[k]]) {
                    int newDist = dist[i][j] + 1;
                    if (newDist < dist[i + rowNum[k]][j + colNum[k]]) {
                        dist[i + rowNum[k]][j + colNum[k]] = newDist;
                        pq.add(new Node(i + rowNum[k], j + colNum[k], newDist));
                        path[i + rowNum[k]][j + colNum[k]] = new ArrayList<>(path[i][j]);
                        path[i + rowNum[k]][j + colNum[k]].add(new Pair<>(i + rowNum[k], j + colNum[k]));
                    }
                }
            }
        }
    
        for (Node exit : exits) {
            if (dist[exit.x][exit.y] < result.distance) {
                result.distance = dist[exit.x][exit.y];
                result.path = path[exit.x][exit.y];
            }
        }
        return result;
    }
    
    static boolean isSafe(int mat[][], int x, int y, int ROW, int COL) {
        return (x >= 0 && x < ROW && y >= 0 && y < COL && mat[x][y] != 1);
    }
    
}
