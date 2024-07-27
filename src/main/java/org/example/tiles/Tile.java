package org.example.tiles;

import lombok.Data;

import java.awt.*;

import static org.example.utilz.Constants.TILE_SIZE;

@Data
public class Tile {
    private int size = TILE_SIZE;
    private TileType type;
    private Color color;

    public Tile(TileType type){
        this.type = type;
        switch (type){
            case ROAD -> color = new Color(135, 62, 32);
            case BUILDABLE -> color = new Color(54, 115, 30, 255);
            case START -> color = Color.BLUE;
            case FINISH -> color = Color.RED;
        }
    }

    public void draw(Graphics g, int i, int j){
        g.setColor(color);
        g.fillRect(i * size, j * size, size, size);
    }
}
