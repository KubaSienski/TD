package org.example.enemies;

import org.example.tiles.Node;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.example.utilz.Constants.TILE_SIZE;

public abstract class Enemy {
    protected int lives = 1;
    protected final EnemyType type = EnemyType.ANGRYFACE;

    protected BufferedImage img;
    protected int x;
    protected int y;


    protected int endX;
    protected int endY;

    protected ArrayList<Node> path;
    protected int pathIndex;
    protected int speedX = 0, speedY = 0;

    public void draw(Graphics g){
        g.drawImage(img, x * TILE_SIZE + speedX, y * TILE_SIZE + speedY, TILE_SIZE, TILE_SIZE, null);
    }
    public void move(){}
}
