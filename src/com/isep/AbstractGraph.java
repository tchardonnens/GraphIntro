package com.isep;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph {
    protected int N;
    protected int M;

    public abstract int degree(int v);

    public double averageDegree() {
        int totalDegree = 0;
        for (int i = 1; i <= N; i++) {
            totalDegree += degree(i);
        }
        return (double) totalDegree / N;
    }

    public int minDegree() {
        int minDegree = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            minDegree = Math.min(minDegree, degree(i));
        }
        return minDegree;
    }

    public int maxDegree() {
        int maxDegree = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            maxDegree = Math.max(maxDegree, degree(i));
        }
        return maxDegree;
    }

    public double edgeDensity() {
        return (double) (2 * M) / (N * (N - 1));
    }

    public List<Integer> isolatedNodes() {
        List<Integer> isolated = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (degree(i) == 0) {
                isolated.add(i);
            }
        }
        return isolated;
    }
}
