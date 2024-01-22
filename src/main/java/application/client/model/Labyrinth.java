package application.client.model;

import application.client.model.field.Bomb;
import application.client.model.field.DestructibleBlock;
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
    private final int x;
    private final int y;
    private Player myPlayer;
    private List<Player> opponents;

    public Labyrinth(char[][] layout) {
        bombMap = new HashMap<>();
        x = layout[0].length;
        y = layout.length;
        this.layout = new Field[y][x];
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
        for (int x = 0; x < this.y; x++) {
            for (int y = 0; y < this.x; y++) {
                switch (layoutUpdate[x][y]) {
                    case 'i' -> setFieldAt(x, y, new IndestructibleBlock());
                    case 'd' -> setFieldAt(x, y, new DestructibleBlock());
                    case 'b' -> setFieldAt(x, y, new Bomb(x, y));
                    default -> setFieldAt(x, y, new Free());
                }
            }
        }
        this.myPlayer = myPlayer;
        this.opponents = opponents;
    }

    public void removePlayer(int positionX, int positionY) {
        opponents.stream() //
                .filter(o -> o.getX() == positionX && o.getY() == positionY) //
                .findFirst() //
                .ifPresent(e -> {
                    opponents.remove(e);
                    setFieldAt(positionX, positionY, new Free());
                });
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
        if (isSmallerThan(x, this.x) || isSmallerThan(y, this.y)) {
            throw new ArrayIndexOutOfBoundsException("Invalid Coordinate x:" + x + " y:" + y);
        }
    }

    private boolean isSmallerThan(int i, int thisI) {
        return i < 0 || i >= thisI;
    }
}
