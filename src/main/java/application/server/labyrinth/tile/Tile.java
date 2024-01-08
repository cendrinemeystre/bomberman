package application.server.labyrinth.tile;

public class Tile {

    private final int x;
    private final int y;

    private TileType type;

    private TileOccupation occupation;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TileType.FREE;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(TileOccupation occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "" + this.type.value;
    }

    public boolean isEmpty() {
        return this.type == TileType.FREE && this.occupation == null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hit() {

        if (this.type == TileType.DESTRUCTIBLE) {
            this.type = TileType.FREE;
            return true;
        }
        return false;

    }
}
