package com.isep;

import java.util.*;
import java.io.*;

// Question 4 : Why is it preferable to use an adjacency list representation in practical contexts ?
/*
Space efficiency: Adjacency list representation is more space-efficient than adjacency matrix representation,
especially for sparse graphs. In an adjacency matrix, the memory usage is O(N^2), where N is the number of nodes.
In contrast, an adjacency list uses memory proportional to the number of edges, O(M), where M is the number of edges.
In many real-world graphs, M is much smaller than N^2, making adjacency lists a better choice.

Time efficiency for common operations: Many graph algorithms rely on iterating over the neighbors of each node.
In an adjacency list representation, this operation takes O(D) time, where D is the degree of the node.
In an adjacency matrix representation, this operation takes O(N) time, regardless of the degree of the node.
For sparse graphs with small degrees, adjacency lists provide a significant speedup for these operations.
*/

public class GraphAdjMatrix extends AbstractGraph {
    private int N; // number of nodes
    private int M; // number of edges
    private boolean[][] adj; // adjacency matrix representation

    // Initialize an empty graph of order N (input parameter) and 0 edges.
    public GraphAdjMatrix(int N) {
        this.N = N;
        this.M = 0;
        adj = new boolean[N + 1][N + 1]; // +1 to accommodate for 1-based indexing of nodes
    }

    // Initializes a graph from a specified input stream.
    public GraphAdjMatrix(InputStream inputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputStream);
        this.N = 6;
        this.M = 4;
        adj = new boolean[N + 1][N + 1]; // +1 to accommodate for 1-based indexing of nodes
        while (scanner.hasNext()) { // read edges until the end of the file
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u][v] = true;
            adj[v][u] = true;
        }
        scanner.close();
    }

    // Add a function that calculates the distance between the nodes using multiplication of the adjacency matrix.
    public int[][] distanceMatrix() {
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = adj[i][j] ? 1 : Integer.MAX_VALUE;
                if (i == j) dist[i][j] = 0;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        return dist;
    }

    // 1. Calculate the degree of a given vertex
    public int degree(int v) {
        int degree = 0;
        for (int i = 1; i <= N; i++) {
            if (adj[v][i]) {
                degree++;
            }
        }
        return degree;
    }

    // 3. Are there any loops?
    public boolean hasLoops() {
        for (int i = 1; i <= N; i++) {
            if (adj[i][i]) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // Read graph from file
        FileInputStream fis = new FileInputStream("src/com/isep/graph.txt");
        GraphAdjMatrix graph = new GraphAdjMatrix(fis);

        // Test distance matrix
        System.out.println("Distance Matrix: " + Arrays.deepToString(graph.distanceMatrix()));

        // Test degree
        System.out.println("Degree of node 1: " + graph.degree(1));
        System.out.println("Degree of node 2: " + graph.degree(2));
        System.out.println("Degree of node 3: " + graph.degree(3));

        System.out.println("Average degree: " + graph.averageDegree());
        System.out.println("Min degree: " + graph.minDegree());
        System.out.println("Max degree: " + graph.maxDegree());
        System.out.println("Edge density: " + graph.edgeDensity());
        System.out.println("Isolated nodes: " + graph.isolatedNodes());
        System.out.println("Has loops: " + graph.hasLoops());
    }
}

// Graph.txt
// Distance Matrix: [[0, 0, 0, 0, 0], [0, 0, 1, 1, 2], [0, 1, 0, 1, 2], [0, 1, 1, 0, 1], [0, 2, 2, 1, 0]]
//  Degree of node 1: 3
//  Degree of node 2: 2
//  Degree of node 3: 3
//  Average degree: 2.25
//  Min degree: 1
//  Max degree: 3
//  Edge density: 1.0
//  Isolated nodes: []
//  Has loops: true

// Facebook Graph Data
// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//	at com.isep.GraphAdjMatrix.<init>(GraphAdjMatrix.java:36)
//	at com.isep.GraphAdjMatrix.main(GraphAdjMatrix.java:136)

// Wiki-Vote.txt
// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//	at com.isep.GraphAdjMatrix.<init>(GraphAdjMatrix.java:36)
//	at com.isep.GraphAdjMatrix.main(GraphAdjMatrix.java:136)
