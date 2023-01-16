package app.tests;

import java.util.List;
import java.util.Vector;

import app.interfaces.ITest;
import app.utils.Djikstra;

public class TestFindPath implements ITest {

    public static void run() {
        int[][] maze = {
            {1,1,1,1,1,1},
            {1,0,0,0,0,1},
            {1,0,1,1,0,1},
            {1,0,1,1,0,1},
            {1,0,0,0,0,1},
            {1,1,1,3,1,1}
        };

        long start = System.currentTimeMillis();
        List<Djikstra.Pair<Integer, Integer>> dijkstraPath = Djikstra.dijkstra(maze, 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("Tempo gasto para encontrar o caminho com Dijkstra: " + (end - start) + " ms");
        System.out.print("Caminho: ");
        for (Djikstra.Pair<Integer, Integer> pair : dijkstraPath) 
            System.out.print("("+pair.first+","+pair.second+")=>");
        System.out.println("\n");

        start = System.currentTimeMillis();
        List<Djikstra.Pair<Integer, Integer>> aStarPath = Djikstra.AStar(maze, 1, 1);
        end = System.currentTimeMillis();
        System.out.println("Tempo gasto para encontrar o caminho com A*: " + (end - start) + " ms");
        System.out.print("Caminho: ");
        for (Djikstra.Pair<Integer, Integer> pair : aStarPath) 
            System.out.print("("+pair.first+","+pair.second+")=>");

    }

}
