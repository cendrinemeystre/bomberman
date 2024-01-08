package application.server.labyrinth.tile;

public enum TileOccupation {

    PLAYER1('1'),
    PLAYER2('2'),
    PLAYER3('3'),
    PLAYER4('4'),
    BOMB('b');

    public final char value;

    TileOccupation(char value) {
        this.value = value;
    }

}
