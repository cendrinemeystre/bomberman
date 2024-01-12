package application.client.view;

import application.client.control.ControlFactory;
import application.client.control.client2server.DropBombControl;
import application.client.control.client2server.MovePlayerControl;
import application.client.model.Game;
import application.client.model.field.Player;
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

    public BombermanPanel() {
        setLayout(new BorderLayout());
        setFocusable(true);
        // Fill panel
        loginPanel = new LoginPanel();
        add(loginPanel.createPanel(), BorderLayout.NORTH);
        labyrinthPanel = new LabyrinthPanel();
        labyrinthPanel.setFocusable(true);
        add(labyrinthPanel.createPanel(), BorderLayout.CENTER);
        messagePanel = new MessagePanel();
        messagePanel.setEnabled(false);
//        messagePanel.setFocusable(false);
        add(messagePanel.createPanel(), BorderLayout.SOUTH);
    }

    public void startGame(Game game) {
        labyrinthPanel.setGame(game);
        labyrinthPanel.updateGameMap();

        requestFocusInWindow();

        // TODO finish implementing this -> Problem with KeyAdapter -> focus: only works before login
        addKeyListener(new KeyAdapter() {
            final MovePlayerControl movePlayerControl = ControlFactory.instance().createClient2ServerControl(MovePlayerControl.class);
            final DropBombControl dropBombControl = ControlFactory.instance().createClient2ServerControl(DropBombControl.class);

            @Override
            public void keyPressed(KeyEvent e) {
                Player player = game.getMyPlayer();
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case 'w' -> movePlayerControl.movePlayer(player.getName(), Direction.UP);
                    case 'a' -> movePlayerControl.movePlayer(player.getName(), Direction.LEFT);
                    case 's' -> movePlayerControl.movePlayer(player.getName(), Direction.DOWN);
                    case 'd' -> movePlayerControl.movePlayer(player.getName(), Direction.RIGHT);
                    case ' ' -> dropBombControl.dropBomb(player.getName(), player.getX(), player.getY());
                }
                System.out.println("key: " + e.getKeyChar());
            }
        });
    }

    public void gameOver(String winnerName, String[] highscoreList) {
        labyrinthPanel.setEnabled(false);
        StringBuilder message = new StringBuilder("Gewinner: " + winnerName + "\nHighscores:\n");
        for (String score : highscoreList) {
            message.append(score).append("\n");
        }
        displayMessage(message.toString());
        loginPanel.enableLogin(true);
    }

    public void update() {
        labyrinthPanel.updateGameMap();
    }

    public void displayMessage(String message) {
        messagePanel.displayMessage(message);
    }
}
