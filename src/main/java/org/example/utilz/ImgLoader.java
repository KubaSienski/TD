package org.example.utilz;

import org.example.tiles.Tile;
import org.example.tiles.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImgLoader {
    public static Tile[][] LoadLevel(String path){
        BufferedImage img = null;
        InputStream is = ImgLoader.class.getResourceAsStream("/levels/" + path);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert img != null;
        Tile[][] tiles = new Tile[img.getWidth()][img.getHeight()];
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x,y));
                if (c.equals(Color.WHITE)){
                    tiles[x][y] = new Tile(TileType.BUILDABLE);
                }
                else if(c.equals(Color.BLACK)){
                    tiles[x][y] = new Tile(TileType.ROAD);
                } else if (c.equals(Color.BLUE)) {
                    tiles[x][y] = new Tile(TileType.START);
                }
                else if (c.equals(Color.RED)) {
                    tiles[x][y] = new Tile(TileType.FINISH);
                }
            }
        return tiles;
    }

    public static BufferedImage LoadEnemy(String path){
        BufferedImage img = null;
        InputStream is = ImgLoader.class.getResourceAsStream("/enemies/" + path);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
