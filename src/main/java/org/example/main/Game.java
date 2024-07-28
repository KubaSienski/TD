package org.example.main;

import lombok.Data;
import org.example.panels.MenuPanel;
import org.example.panels.PlayPanel;
import org.example.utilz.GameStates;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Data
public class Game implements Runnable{
    JPanel mainPanel = new JPanel(new CardLayout());
    MenuPanel menuPanel;
    PlayPanel playPanel;
    GWindow gWindow;
    CardLayout layout;

    public Game(){
        loadPanels();

        gWindow = new GWindow(mainPanel);
        gWindow.add(mainPanel);

        layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "menu");

        gWindow.setVisible(true);

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void loadPanels() {
        menuPanel = new MenuPanel();
        playPanel = new PlayPanel(this);

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(playPanel, "play");
    }

    public void update(){
        if (Objects.requireNonNull(GameStates.state) == GameStates.PLAYING) {
            playPanel.update();
        }
    }

    @Override
    public void run() {
        final int FPS_SET = 120;
        final int UPS_SET = 200;
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;

        //game loop
        while (true) {
            switch (GameStates.state){
                case MENU ->  layout.show(mainPanel, "menu");
                case PLAYING -> layout.show(mainPanel, "play");
            }
            // repainting every frame
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }
            if (deltaF >= 1) {
                mainPanel.repaint();
                deltaF--;
            }
        }
    }
}
