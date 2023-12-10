package application.client.model;

public class Player {
    private String name;
    private int x, y;

    public Player(String playerName) {
        name = playerName;
    }

    public Player(String name, int initialX, int initialY) {
        this.name = name;
        x = initialX;
        y = initialY;
    }

    public boolean isYourName(String playerName) {
        return name.equals(playerName);
    }

    public void setPosition(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }
}
