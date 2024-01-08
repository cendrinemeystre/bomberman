package application.client.model;

import application.client.model.field.FieldType;
import application.client.model.field.Player;
import protocol.Direction;
import protocol.server2client.PlayerJoined;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> opponents = new ArrayList<>();
    private Labyrinth labyrinth;
    private Player myPlayer;

    public Game() {
        // TODO remove
        myPlayer = new Player("oen");
        myPlayer.setPosition(1, 2);
        opponents.add(new Player("playerName", 3, 0, FieldType.OPPONENT_1));
        opponents.add(new Player("playerName2", 1, 1, FieldType.OPPONENT_2));
        opponents.add(new Player("playerNam3", 2, 3, FieldType.OPPONENT_3));

    }

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void initializeLabyrinth(int height, int width) {
        labyrinth = new Labyrinth(height, width);
    }

    public void setLabyrinthLayout(char[][] layout) {
        labyrinth.setLayout(layout, myPlayer, opponents);
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        if (myPlayer.isName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            FieldType fieldType = getFieldType(opponents.size());
            opponents.add(new Player(playerName, initialX, initialY, fieldType));
        }
    }

    public void playerMoved(String playerName, Direction direction) {
        Player player = getPlayer(playerName);
        int nextX = player.getX();
        int nextY = player.getY();
        switch (direction) {
            case UP -> nextX--;
            case DOWN -> nextX++;
            case LEFT -> nextY--;
            case RIGHT -> nextY++;
        }
        labyrinth.movePlayerTo(player.getX(), player.getY(), nextX, nextY);
        player.setPosition(nextX, nextY);
    }

    public void playerHit(String playerName) {
        Player player = getPlayer(playerName);
        labyrinth.removePlayer(player.getX(), player.getY());
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    private Player getPlayer(String playerName) {
        if (!myPlayer.isName(playerName)) {
            for (Player player : opponents) {
                if (player.isName(playerName)) {
                    return player;
                }
            }
        }
        return myPlayer;
    }

    private FieldType getFieldType(int size) {
        FieldType fieldType = null;
        for (FieldType field : FieldType.values()) {
            if (field.getKey() == size) {
                fieldType = field;
            }
        }
        return fieldType;
    }

}
