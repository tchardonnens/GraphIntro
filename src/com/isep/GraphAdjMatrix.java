package com.isep;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphAdjMatrix {
    private int N;
    private int M;
    private boolean[][] adj;

    public GraphAdjMatrix(int N) {
        this.N = N;
        this.M = 0;
        adj = new boolean[N][N];
    }

    public GraphAdjMatrix(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            N = scanner.nextInt();
            M = scanner.nextInt();
            adj = new boolean[N][N];

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

    public void addEdge(int u, int v) {
        adj[u][v] = true;
        adj[v][u] = true;
        M++;
    }

    public int[][] calculateDistances() {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (adj[i][j]) {
                    dist[i][j] = 1;
                } else if (i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                } else {
                    dist[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

}

