package org.example.panels;

import lombok.Getter;
import org.example.enemies.AngryFace;
import org.example.main.Game;
import org.example.tiles.Tile;
import org.example.tiles.TileType;

import javax.swing.*;
import java.awt.*;

import static org.example.utilz.Constants.WINDOW_WIDTH;
import static org.example.utilz.Constants.WINDOW_HEIGHT;
import static org.example.utilz.ImgLoader.LoadLevel;

@Getter
public class PlayPanel extends JPanel {
    private Game game;
    private Tile[][] map;
    private int startX, startY;
    private int endX, endY;
    private AngryFace angryFace;

    public PlayPanel(Game game){
        this.game = game;
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        map = LoadLevel("basicLvl.png");
        getStartEnd();
        angryFace = new AngryFace(startX,startY,endX,endY);
        angryFace.findPath(map);
    }

    public void update() {
        angryFace.move();
    }

    private void getStartEnd() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].getType() == TileType.START) {
                    startX = i;
                    startY = j;
                }
                else if (map[i][j].getType() == TileType.FINISH) {
                    endX = i;
                    endY = j;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i< map.length; i++){
            for (int j = 0; j<map[i].length; j++) {
                map[i][j].draw(g, i, j);
            }
        }
        angryFace.draw(g);
    }
}
