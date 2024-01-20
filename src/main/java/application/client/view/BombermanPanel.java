package application.client.view;

import application.client.model.Game;
import application.client.view.panel.LabyrinthPanel;
import application.client.view.adapter.BombermanKeyAdapter;
import application.client.view.panel.top.LoginPanel;
import application.client.view.panel.bottom.MessagePanel;

import javax.swing.*;
import java.awt.*;

public class BombermanPanel extends JPanel {
    private final LoginPanel loginPanel;
    private final LabyrinthPanel labyrinthPanel;
    private final MessagePanel messagePanel;
    private BombermanKeyAdapter keyAdapter;

    public BombermanPanel() {
        setLayout(new BorderLayout());
        setFocusable(true);
        // Fill panel
        loginPanel = new LoginPanel();
        add(loginPanel.createPanel(), BorderLayout.NORTH);
        labyrinthPanel = new LabyrinthPanel();
        add(labyrinthPanel.createPanel(), BorderLayout.CENTER);
        messagePanel = new MessagePanel();
        add(messagePanel.createPanel(), BorderLayout.SOUTH);
    }

    public void startGame(Game game) {
        labyrinthPanel.setGame(game);
        labyrinthPanel.updateGameMap();
        requestFocusInWindow();
        keyAdapter = new BombermanKeyAdapter(game.getMyPlayer());
        addKeyListener(keyAdapter);
    }

    public void gameOver(String winnerName, String[] highscoreList) {
        labyrinthPanel.setEnabled(false);
        StringBuilder message = new StringBuilder("Gewinner: " + winnerName + "\nHighscores:\n");
        for (String score : highscoreList) {
            message.append(score).append("\n");
        }
        displayMessage(message.toString());
        removeKeyListener(keyAdapter);
        loginPanel.enableLogin(true);
    }

    public void update() {
        labyrinthPanel.updateGameMap();
    }

    public void displayMessage(String message) {
        messagePanel.displayMessage(message);
    }
}
