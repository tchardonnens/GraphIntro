package com.isep;

import java.util.List;

public interface Graph {
    int degree(int v);
    double averageDegree();
    int minDegree();
    int maxDegree();
    double edgeDensity();
    List<Integer> isolatedNodes();
    boolean hasLoops();
}
