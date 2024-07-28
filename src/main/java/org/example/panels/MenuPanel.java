package org.example.panels;

import org.example.main.Game;
import org.example.utilz.GameStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.utilz.Constants.WINDOW_HEIGHT;
import static org.example.utilz.Constants.WINDOW_WIDTH;

public class MenuPanel extends JPanel {
    private Game game;
    private JButton play = new JButton("PLAY!");

    public MenuPanel(Game game) {
        this.game = game;
        setupButtons();

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    private void setupButtons() {
        // PLAY BUTTON
        play.setBounds(200, 200, 100, 70);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameStates.state = GameStates.PLAYING;
            }
        });
        add(play);
    }
}
