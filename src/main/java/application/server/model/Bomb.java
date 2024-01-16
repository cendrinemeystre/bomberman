package application.server.model;

public class Bomb {
    private final String id;
    public static final char ICON = 'b';
    private int x;
    private int y;
    private boolean exploded;

    public Bomb(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.exploded = false;
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

    public boolean isExploded() {
        return exploded;
    }

    public String getId() {
        return id;
    }

    public boolean checkIfHit(int x, int y) {
        if (x < this.x + 1 && x > this.x - 1) {
            return y < this.y + 1 && y > this.y - 1;
        }
        return false;
    }

    public void explode() {
        exploded = true;
    }
}
