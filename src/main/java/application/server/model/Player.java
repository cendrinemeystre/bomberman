package application.server.model;

import network.Message;
import protocol.server2client.PlayerJoined;

public class Player {
    private final String name;
    private final String connectionId;
    private int x;
    private int y;
    private boolean alive;
    private int score;

    public Player(String name, String connectionId, int[] position) {
        this.name = name;
        this.connectionId = connectionId;
        this.x = position[0];
        this.y = position[1];
        score = 0;
        alive = true;
    }

    public boolean isYourName(String playerName) {
        return name.equals(playerName);
    }

    public String getName() {
        return name;
    }

    public Message createPlayerJoined() {
        return new PlayerJoined(name, x, y);
    }

    public String getConnectionId() {
        return connectionId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void kill() {
        score += 10;
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getScore() {
        return score;
    }
}
