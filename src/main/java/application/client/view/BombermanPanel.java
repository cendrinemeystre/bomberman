package application.client.view;

import application.client.control.ControlFactory;
import application.client.control.client2server.DropBombControl;
import application.client.control.client2server.MovePlayerControl;
import application.client.model.Game;
import application.client.model.field.Player;
import application.client.view.adapter.BombermanKeyAdapter;
import application.client.view.panel.LabyrinthPanel;
import application.client.view.panel.LoginPanel;
import application.client.view.panel.MessagePanel;
import protocol.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        //highscorePanel.display(winnerName, highscoreList);
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
