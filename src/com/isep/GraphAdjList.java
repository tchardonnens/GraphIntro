package com.isep;

import java.util.*;
import java.io.*;

public class GraphAdjList {
    private int N; // number of nodes
    private int M; // number of edges
    private List<List<Integer>> adj; // adjacency list representation

    // Initialize an empty graph of order N (input parameter) and 0 edges.
    public GraphAdjList(int N) {
        this.N = N;
        this.M = 0;
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Initializes a graph from a specified input stream.
    public GraphAdjList(InputStream inputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputStream);
        this.N = 4; // number of nodes is 4
        this.M = 6; // number of edges is 6
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) { // <= N to accommodate for 1-based indexing of nodes
            adj.add(new ArrayList<>());
        }
        while (scanner.hasNext()) { // read edges until the end of the file
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            addEdge(u, v);
        }
        scanner.close();
    }


    // Return the total order and the size of the graph.
    public int getOrder() {
        return N;
    }

    public int getSize() {
        return M;
    }

    // Add an edge between two existing nodes to the graph.
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Create a function Neighbors(int v) that takes as an input a given vertex and prints all the neighbors of that vertex.
    public void neighbors(int v) {
        System.out.println("Neighbors of vertex " + v + ": " + adj.get(v));
    }

    // Add a function that prints the Adjacency list representation of the graph.
    public void printAdjList() {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println(i + " -> " + adj.get(i));
        }
    }

    // Create a function called Degree(int v) that returns for a given vertex v, its degree.
    public int degree(int v) {
        return adj.get(v).size();
    }

    // 1. What is the average, minimal and maximal degree of the graph?
    public double averageDegree() {
        int totalDegree = 0;
        for (int i = 0; i < N; i++) {
            totalDegree += degree(i);
        }
        return (double) totalDegree / N;
    }

    public int minDegree() {
        int minDegree = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            minDegree = Math.min(minDegree, degree(i));
        }
        return minDegree;
    }

    public int maxDegree() {
        int maxDegree = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxDegree = Math.max(maxDegree, degree(i));
        }
        return maxDegree;
    }

    // 1. What is the edge-density?
    public double edgeDensity() {
        return (double) (2 * M) / (N * (N - 1));
    }

    // 2. Are there any isolated nodes? If yes, which ones?
    public List<Integer> isolatedNodes() {
        List<Integer> isolated = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (degree(i) == 0) {
                isolated.add(i);
            }
        }
        return isolated;
    }

    // 3. Are there any loops?
    public boolean hasLoops() {
        for (int i = 0; i < N; i++) {
            if (adj.get(i).contains(i)) {
                return true;
            }
        }
        return false;
    }

    // Read graph data from keyboard input
    public static GraphAdjList readGraphFromInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of vertices? ");
        int n = scanner.nextInt();
        System.out.print("Number of edges? ");
        int m = scanner.nextInt();
        GraphAdjList graph = new GraphAdjList(n);
        for (int i = 0; i < m; i++) {
            System.out.print("Edge " + (i + 1) + "? ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }
        scanner.close();
        return graph;
    }

    // Main function to test the above methods
    public static void main(String[] args) throws FileNotFoundException {
        // Read graph from file
        FileInputStream fis = new FileInputStream("src/com/isep/graph.txt");
        GraphAdjList graph = new GraphAdjList(fis);
        graph.printAdjList();

        // Test functions
        graph.neighbors(1);
        System.out.println("Degree of vertex 1: " + graph.degree(1));
        System.out.println("Average degree: " + graph.averageDegree());
        System.out.println("Min degree: " + graph.minDegree());
        System.out.println("Max degree: " + graph.maxDegree());
        System.out.println("Edge density: " + graph.edgeDensity());
        System.out.println("Isolated nodes: " + graph.isolatedNodes());
        System.out.println("Has loops: " + graph.hasLoops());

        // Read graph from keyboard input
        GraphAdjList inputGraph = readGraphFromInput();
        inputGraph.printAdjList();
    }
}
