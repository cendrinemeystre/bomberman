package application.client.model;

import protocol.Direction;
import protocol.server2client.PlayerJoined;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Labyrinth labyrinth;
    private Player myPlayer;
    private final List<Player> opponents = new ArrayList<>();

    public Game() {
    }

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        if (myPlayer.isName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            opponents.add(new Player(playerName, initialX, initialY));
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

}
