package application.client.model;

import application.client.model.field.Bomb;
import application.client.model.field.Field;
import application.client.model.field.Free;
import application.client.model.field.IndestructibleBlock;
import application.client.model.field.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Labyrinth {
    private final Map<String, Bomb> bombMap;
    private final Field[][] layout;
    private final int y;
    private final int x;
    private Player myPlayer;
    private List<Player> opponents;

    public Labyrinth(char[][] layout) {
        bombMap = new HashMap<>();
        x = layout.length;
        y = layout[0].length;
        this.layout = new Field[x][y];
    }

    public Field[][] getLayoutForRendering() {
        Field[][] layoutCopy = copyLayout();
        bombMap.values()
                .forEach(bomb -> layoutCopy[bomb.getPositionY()][bomb.getPositionX()] = bomb);
        opponents.forEach(opponent -> layoutCopy[opponent.getY()][opponent.getX()] = opponent);
        layoutCopy[myPlayer.getY()][myPlayer.getX()] = myPlayer;
        return layoutCopy;
    }

    public void setLayout(char[][] layoutUpdate, Player myPlayer, List<Player> opponents) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                switch (layoutUpdate[y][x]) {
                    case 'i' -> setFieldAt(y, x, new IndestructibleBlock());
                    case 'd' -> setFieldAt(y, x, new Bomb(y, x));
                    default -> setFieldAt(y, x, new Free());
                }
            }
        }
        this.myPlayer = myPlayer;
        this.opponents = opponents;
    }

    public void removePlayer(int positionX, int positionY) {
        setFieldAt(positionX, positionY, new Free());
    }

    public void addBomb(String id, int x, int y) {
        this.bombMap.put(id, new Bomb(x, y));
    }

    public void removeBomb(String id) {
        this.bombMap.remove(id);
    }

    private Field[][] copyLayout() {
        Field[][] layoutCopy = new Field[y][x];
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                layoutCopy[y][x] = getFieldAt(x, y);
            }
        }
        return layoutCopy;
    }

    private Field getFieldAt(int x, int y) {
        isValidCoordinate(x, y);
        return layout[y][x];
    }

    private void setFieldAt(int x, int y, Field field) {
        isValidCoordinate(x, y);
        layout[y][x] = field;
    }

    private void isValidCoordinate(int x, int y) {
        if (isNotValidCoordinate(x, y)) {
            throw new ArrayIndexOutOfBoundsException("Invalid Coordinate x:" + x + " y:" + y);
        }
    }

    private boolean isNotValidCoordinate(int x, int y) {
        return x < 0 || x >= this.y
                || y < 0 || y >= this.x;
    }
}
