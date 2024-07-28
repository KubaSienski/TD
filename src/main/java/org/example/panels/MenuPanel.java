package org.example.panels;

import org.example.utilz.GameStates;

import javax.swing.*;
import java.awt.*;

import static org.example.utilz.Constants.WINDOW_HEIGHT;
import static org.example.utilz.Constants.WINDOW_WIDTH;

public class MenuPanel extends JPanel {
    private final JButton play = new JButton("PLAY!");

    public MenuPanel() {
        setupButtons();

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    private void setupButtons() {
        // PLAY BUTTON
        play.setBounds(200, 200, 100, 70);
        play.addActionListener(e -> GameStates.state = GameStates.PLAYING);
        add(play);
    }
}
