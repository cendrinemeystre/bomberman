package application.server.labyrinth.tile;

public enum TileType {

    FREE('f'),
    DESTRUCTIBLE('d'),
    INDESTRUCTIBLE('i');

    public final char value;

    TileType(char value) {
        this.value = value;
    }
}
