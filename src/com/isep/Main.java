package com.isep;

public class Main {
    public static void main(String[] args) {
        // Test GraphAdjList
        String fileName = "src/com/isep/graph.txt";
        GraphAdjList graphAdjList = new GraphAdjList(fileName);

        System.out.println("GraphAdjList:");
        graphAdjList.printGraph();

        // Test GraphAdjMatrix
        GraphAdjMatrix graphAdjMatrix = new GraphAdjMatrix(fileName);
        int[][] distances = graphAdjMatrix.calculateDistances();

        System.out.println("\nGraphAdjMatrix distances:");
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }
    }
}
