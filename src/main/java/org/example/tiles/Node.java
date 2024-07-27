package org.example.tiles;

import lombok.Data;

@Data
public class Node {
    private int x, y;
    private int gCost, hCost, fCost;
    private Node parent;

    public Node(int x, int y, int gCost, int hCost, Node parent) {
        this.x = x;
        this.y = y;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
        this.parent = parent;
    }
}
