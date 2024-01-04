package application.client.model;

public enum FieldType {
    INDESTRUCTIBLE_BLOCK('i'),
    DESTRUCTIBLE_BLOCK('d'),
    BOMB('b'),
    FREE('f'),
    PLAYER1('1'),
    PLAYER2('2'),
    PLAYER3('3'),
    PLAYER4('4');

    private final char key;

    FieldType(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }
}
