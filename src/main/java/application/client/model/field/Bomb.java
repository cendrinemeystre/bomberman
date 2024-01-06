package application.client.model.field;

public class Bomb extends Field {
    private final int positionX;
    private final int positionY;

    public Bomb(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        type = FieldType.BOMB;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
