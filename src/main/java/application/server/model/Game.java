package application.server.model;

import application.server.labyrinth.Labyrinth;
import protocol.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private static final int NB_OF_PLAYERS = 4;
    Random random = new Random();
    private List<Player> players = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private Labyrinth labyrinth;

    public Game(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public boolean numberOfPlayersComplete() {
        return players.size() == NB_OF_PLAYERS;
    }

    public boolean isUnique(String playerName) {
        for (Player player : players) {
            if (player.isYourName(playerName)) {
                return false;
            }
        }
        return true;
    }

    public Player createPlayer(String playerName, String connectionId) {
        Player player = new Player(playerName, connectionId, getRandomPosition());
        players.add(player);
        return player;
    }

    private int[] getRandomPosition() {
        return new int[]{random.nextInt(1, labyrinth.getWidth() + 1), random.nextInt(1, labyrinth.getHeight() + 1)};
    }

    public boolean isMovePossible(String playerName, Direction direction) {

        return switch (direction) {
            case UP -> labyrinth.isTileEmpty(getPlayerByName(playerName).getX(),
                    getPlayerByName(playerName).getY() - 1);
            case DOWN -> labyrinth.isTileEmpty(getPlayerByName(playerName).getX(),
                    getPlayerByName(playerName).getY() + 1);
            case LEFT -> labyrinth.isTileEmpty(getPlayerByName(playerName).getX() - 1,
                    getPlayerByName(playerName).getY());
            case RIGHT -> labyrinth.isTileEmpty(getPlayerByName(playerName).getX() + 1,
                    getPlayerByName(playerName).getY());
        };

    }

    public void movePlayer(String playerName, Direction direction) {

        switch (direction) {
            case UP -> getPlayerByName(playerName).setY(getPlayerByName(playerName).getY() - 1);
            case DOWN -> getPlayerByName(playerName).setY(getPlayerByName(playerName).getY() + 1);
            case LEFT -> getPlayerByName(playerName).setX(getPlayerByName(playerName).getX() - 1);
            case RIGHT -> getPlayerByName(playerName).setX(getPlayerByName(playerName).getX() + 1);
        }

    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    private Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.isYourName(playerName)) {
                return player;
            }
        }
        return null;
    }

    public String dropBomb(int x, int y) {
        Bomb newBomb = new Bomb("bomb_" + bombs.size(), x, y);
        bombs.add(newBomb);
        labyrinth.dropBomb(x, y);
        return newBomb.getId();
    }

    public boolean bombTimer(Bomb bomb) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!bomb.isExploded()) {
                    bomb.explode();
                }
            }
        }, 2000);

        return true;
    }

    public List<String> checkPlayerHit(Bomb bomb) {

        List<String> hitPlayers = new ArrayList<>();

        for (Player player : players) {
            if (bomb.checkIfHit(player.getX(), player.getY())) {
                hitPlayers.add(player.getName());
            }
        }
        return hitPlayers;
    }

    public void updateLabyrinth(Bomb bomb) {
        labyrinth.bombExploded(bomb);
    }

    public Bomb getBombById(String id) {

        return bombs.stream().filter(bomb -> bomb.getId().equals(id)).findFirst().get();

    }

    public void logScore(String playerName) {
        for (Player player : players) {
            if (player.isYourName(playerName)) {
                player.kill();
            }
        }
    }

    public void killPlayer(String playerName) {
        for (Player player : players) {
            if (player.isYourName(playerName)) {
                player.die();
            }
        }
    }

    public boolean checkIfRunning() {
        int i = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                i++;
            }
        }
        return i > 1;
    }

    public String getWinner() {
        Player winner = players.getFirst();
        for (Player player : players) {
            if (player.getScore() > winner.getScore()) {

                winner = player;
            }
        }
        return winner.getName();
    }

    public String[] getScoreboard() {
        String[] scoreboard = new String[players.size()];
        Collections.sort(players, Comparator.comparingInt(Player::getScore));
        for (int i = 0; i < scoreboard.length; i++) {
            scoreboard[i] = players.get(i).getScore() + ": " + players.get(i).getName();
        }
        return scoreboard;
    }
}
