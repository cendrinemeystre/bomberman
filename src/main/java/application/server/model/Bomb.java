package application.server.model;

public class Bomb {
    public static final char ICON = 'b';
    private final String id;
    private int x;
    private int y;

    public Bomb(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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

    public String getId() {
        return id;
    }

    public boolean checkIfHit(int x, int y) {
        return (this.y == y && (this.x - 1 <= x || this.x + 1 >= x))
                || (this.x == x && (this.y + 1 >= y || this.y - 1 <= y));
    }

}
