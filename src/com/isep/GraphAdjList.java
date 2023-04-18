package com.isep;

import java.util.*;
import java.io.*;

public class GraphAdjList extends AbstractGraph {
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
        this.N = 6;
        this.M = 4;
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

    public int countEdges() {
        int totalEdges = 0;
        for (int i = 1; i <= N; i++) {
            totalEdges += adj.get(i).size();
        }
        M = totalEdges / 2;
        return totalEdges / 2;
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
        graph.countEdges();
        System.out.println("Number of edges: " + graph.getSize());

        // Test functions
        graph.neighbors(1);
        System.out.println("Degree of vertex 1: " + graph.degree(1));
        System.out.println("Average degree: " + graph.averageDegree());
        System.out.println("Min degree: " + graph.minDegree());
        System.out.println("Max degree: " + graph.maxDegree());
        System.out.println("Edge density: " + graph.edgeDensity());
        //System.out.println("Isolated nodes: " + graph.isolatedNodes());
        System.out.println("Has loops: " + graph.hasLoops());

        // Read graph from keyboard input
        GraphAdjList inputGraph = readGraphFromInput();
        inputGraph.printAdjList();
    }
}

// Graph.txt graph data
// N = 6
// 0 -> []
// 1 -> [1, 1, 2, 3]
// 2 -> [1, 3, 3]
// 3 -> [1, 2, 2, 4]
// 4 -> [3]
// 5 -> []
// 6 -> []
// Number of edges: 6
// Neighbors of vertex 1: [1, 1, 2, 3]
// Degree of vertex 1: 4
// Average degree: 2.0
// Min degree: 0
// Max degree: 4
// Edge density: 0.4
// Has loops: true


// Facebook graph data
// Number of edges: 88060
// Neighbors of vertex 1: [0, 48, 53, 54, 73, 88, 92, 119, 126, 133, 194, 236, 280, 299, 315, 322, 346]
// Degree of vertex 1: 17
// Average degree: 2.0
// Min degree: 0
// Max degree: 1045
// Edge density: -2.1884130981866974E-4
// Has loops: false

// Wikipedia graph data
// Neighbors of vertex 1: []
// Degree of vertex 1: 0
// Average degree: 2.0
// Min degree: 0
// Max degree: 1167
// Edge density: -9.71964311893418E-5
// Has loops: false
