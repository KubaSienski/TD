package org.example.enemies;

import org.example.tiles.Node;
import org.example.tiles.Tile;
import org.example.tiles.TileType;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;
import static org.example.utilz.Constants.TILE_SIZE;
import static org.example.utilz.ImgLoader.LoadEnemy;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AngryFace {
    private BufferedImage img;
    private int lives = 1;
    private EnemyType type = EnemyType.ANGRYFACE;
    private int x, y, endX, endY;
    private ArrayList<Node> path;
    private int pathIndex;
    private final int speed = 1;

    public AngryFace(int x, int y, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.path = new ArrayList<>();
        this.pathIndex = 0;
        img = LoadEnemy("AngryFace.png");
    }

    public void move() {
        if (pathIndex < path.size()) {
            Node nextNode = path.get(pathIndex);
            x = nextNode.getX();
            y = nextNode.getY();
            pathIndex++;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

    public void findPath(Tile[][] map) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getGCost));
        boolean[][] closedSet = new boolean[map.length][map[0].length];

        Node startNode = new Node(x, y, 0, 0, null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            closedSet[currentNode.getX()][currentNode.getY()] = true;

            if (currentNode.getX() == endX && currentNode.getY() == endY) {
                reconstructPath(currentNode);
                return;
            }

            for (int[] direction : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                int newX = currentNode.getX() + direction[0];
                int newY = currentNode.getY() + direction[1];

                if (isInBounds(map, newX, newY) &&
                        (map[newX][newY].getType() == TileType.ROAD || map[newX][newY].getType() == TileType.FINISH) &&
                        !closedSet[newX][newY]) {
                    int newGCost = currentNode.getGCost() + 1;
                    Node neighborNode = new Node(newX, newY, newGCost, 0, currentNode);

                    openSet.add(neighborNode);
                }
            }
        }

        System.out.println("Path not found.");
    }


    private void reconstructPath(Node endNode) {
        Node current = endNode;
        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }
    }

    private boolean isInBounds(Tile[][] map, int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
}