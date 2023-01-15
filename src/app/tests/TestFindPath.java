package app.tests;

import java.util.List;
import java.util.Vector;

import app.interfaces.ITest;
import app.utils.Djikstra;

public class TestFindPath implements ITest {

    public static void run() {
        int rows = 6;
        int cols = 6;
        int[][] maze = {
            {1,1,1,1,1,1},
            {1,0,0,0,0,1},
            {1,0,1,1,0,1},
            {1,0,1,1,0,1},
            {1,0,0,0,0,1},
            {1,1,1,3,1,1}
        };
        Djikstra.Result result = Djikstra.dijkstra(maze, 6, 6, 1, 2);
        //p= printe o caminho
        System.out.println("Path: ");
        for (int i = 0; i < result.path.size(); i++) {
            System.out.println(result.path.get(i).first + " " + result.path.get(i).second);
        }
        System.out.println("Distance: " + result.distance);

    }

}
