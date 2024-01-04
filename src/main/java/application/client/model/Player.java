package application.client.model;

public class Player {
    private String name;
    private int x;
    private int y;

    public Player(String playerName) {
        name = playerName;
    }

    public Player(String name, int initialX, int initialY) {
        this.name = name;
        x = initialX;
        y = initialY;
    }

    public boolean isName(String playerName) {
        return name.equals(playerName);
    }

    public void setPosition(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }

    public String getName() {
        return name;
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
}
