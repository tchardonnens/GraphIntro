package com.isep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphAdjList {
    private int N;
    private int M;
    private List<List<Integer>> adj;

    public GraphAdjList(int N) {
        this.N = N;
        this.M = 0;
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public GraphAdjList(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            N = scanner.nextInt();
            M = scanner.nextInt();
            adj = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
            }

            while (scanner.hasNext()) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                addEdge(u, v);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getOrder() {
        return N;
    }

    public int getSize() {
        return M;
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
        M++;
    }

    public List<Integer> neighbors(int v) {
        return adj.get(v);
    }

    public void printGraph() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + ": ");
            for (int neighbor : adj.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public int degree(int v) {
        return adj.get(v).size();
    }

}
