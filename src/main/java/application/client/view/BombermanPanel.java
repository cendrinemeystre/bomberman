package application.client.view;

import application.client.control.ControlFactory;
import application.client.control.client2server.DropBombControl;
import application.client.control.client2server.MovePlayerControl;
import application.client.model.Game;
import application.client.model.Player;
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
        add(labyrinthPanel.createPanel(), BorderLayout.CENTER);
        messagePanel = new MessagePanel();
        add(messagePanel.createPanel(), BorderLayout.SOUTH);

        char[][] initialGameState = {
                {'i', 'i', 'i', 'i', 'i'},
                {'i', '2', 'f', 'f', 'i'},
                {'i', 'f', 'i', '3', 'i'},
                {'1', 'b', 'f', 'f', 'i'},
                {'i', 'i', 'i', 'i', 'i'}};
        startGame(initialGameState, new Game());
    }

    public void startGame(char[][] map, Game game) {
        labyrinthPanel.setEnabled(true);
        labyrinthPanel.setGame(game);
        labyrinthPanel.updateGameMap(map);

        labyrinthPanel.addKeyListener(new KeyAdapter() {
//            final MovePlayerControl movePlayerControl = ControlFactory.instance().createMovePlayerControl();
//            final DropBombControl dropBombControl = ControlFactory.instance().createDropBombControl();

            @Override
            public void keyPressed(KeyEvent e) {
                Player player = game.getMyPlayer();
//                switch (e.getKeyChar()) {
//                    case 'W' -> movePlayerControl.movePlayer(player.getName(), Direction.UP);
//                    case 'A' -> movePlayerControl.movePlayer(player.getName(), Direction.LEFT);
//                    case 'S' -> movePlayerControl.movePlayer(player.getName(), Direction.DOWN);
//                    case 'D' -> movePlayerControl.movePlayer(player.getName(), Direction.RIGHT);
//                    case ' ' -> dropBombControl.dropBomb(player.getName(), player.getX(), player.getY());
//                }
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
    }

    public void update(char[][] map) {
        labyrinthPanel.updateGameMap(map);
    }

    public void playerMoved(String playerName, Direction direction) {
        labyrinthPanel.playerMoved(playerName, direction);
    }

    public void bombDropped(String id, int positionX, int positionY) {
        labyrinthPanel.bombDropped(id, positionX, positionY);
    }

    public void bombExploded(String id) {
        labyrinthPanel.bombExploded(id);
    }

    public void playerHit(String playerName) {
        labyrinthPanel.playerHit(playerName);
    }

    public void displayMessage(String message) {
        messagePanel.displayMessage(message);
    }

}
