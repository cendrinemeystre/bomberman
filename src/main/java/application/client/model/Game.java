package application.client.model;

import application.client.model.field.Field;
import application.client.model.field.FieldType;
import application.client.model.field.Player;
import protocol.Direction;
import protocol.server2client.PlayerJoined;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Game {
    private final List<Player> opponents = new ArrayList<>();
    private Labyrinth labyrinth;
    private Player myPlayer;

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void initializeLabyrinth(char[][] map) {
        labyrinth = new Labyrinth(map);
    }

    public void setLabyrinthLayout(char[][] layout) {
        labyrinth.setLayout(layout, myPlayer, opponents);
    }

    public boolean isMyPlayer(PlayerJoined message){
        return myPlayer.isName(message.getPlayerName());
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        // DO NOT REMOVE!!!! Somehow this fixes a race condition??????
        System.out.println("Player joined: " + playerName + " x=" + initialX + " y=" + initialY);
        if (myPlayer.isName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            FieldType fieldType = getOpponentFieldType(opponents.size());
            opponents.add(new Player(playerName, initialX, initialY, fieldType));
        }
    }

    public void playerMoved(String playerName, Direction direction) {
        Player player = getPlayer(playerName);
        int nextX = player.getX();
        int nextY = player.getY();
        switch (direction) {
            case UP -> nextY--;
            case DOWN -> nextY++;
            case LEFT -> nextX--;
            case RIGHT -> nextX++;
        }
        player.setPosition(nextX, nextY);
    }

    public void bombDropped(String id, int x, int y){
        labyrinth.addBomb(id, x, y);
    }

    public void removeBomb(String id){
        labyrinth.removeBomb(id);
    }

    public void playerHit(String playerName) {
        Player player = getPlayer(playerName);
        labyrinth.removePlayer(player.getX(), player.getY());
    }

    public void gameOver(){
        myPlayer = null;
        opponents.clear();
        labyrinth = null;
    }

    public Field[][] getLabyrinthLayoutForRendering() {
        return labyrinth.getLayoutForRendering();
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    private FieldType getOpponentFieldType(int size) {
        return switch (size) {
            case 0 -> FieldType.OPPONENT_1;
            case 1 -> FieldType.OPPONENT_2;
            case 2 -> FieldType.OPPONENT_3;
            default -> throw new IllegalStateException("Invalid number of opponents: " + size);
        };
    }

    private Player getPlayer(String playerName) {
        if (myPlayer.isName(playerName)) {
            return myPlayer;
        }
        return opponents.stream()
                .filter(opponent -> opponent.isName(playerName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Player with name " + playerName + " was not found"));
    }
}
