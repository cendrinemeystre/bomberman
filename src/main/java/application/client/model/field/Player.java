package application.client.model.field;

public class Player extends Field {
    private final String name;
    private int x;
    private int y;

    public Player(String playerName) {
        name = playerName;
        type = FieldType.MY_PLAYER;
    }

    public Player(String name, int initialX, int initialY, FieldType fieldType) {
        this.name = name;
        x = initialX;
        y = initialY;
        type = fieldType;
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

    public int getY() {
        return y;
    }
}
