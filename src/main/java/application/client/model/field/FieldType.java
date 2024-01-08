package application.client.model.field;

public enum FieldType {
    INDESTRUCTIBLE_BLOCK('i'),
    DESTRUCTIBLE_BLOCK('d'),
    BOMB('b'),
    FREE('f'),
    MY_PLAYER('3'),
    OPPONENT_1('0'),
    OPPONENT_2('1'),
    OPPONENT_3('2');

    private final char key;

    FieldType(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }
}
