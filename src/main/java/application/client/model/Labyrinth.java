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
    private final int height;
    private final int width;
    private Player myPlayer;
    private List<Player> opponents;

    public Labyrinth(char[][] layout) {
        bombMap = new HashMap<>();
        height = layout.length;
        width = layout[0].length;
        this.layout = new Field[height][width];
    }

    public Field[][] getLayoutForRendering() {
        Field[][] layoutCopy = copyLayout();
        bombMap.values()
                .forEach(bomb -> layoutCopy[bomb.getPositionX()][bomb.getPositionY()] = bomb);
        opponents.forEach(opponent -> layoutCopy[opponent.getX()][opponent.getY()] = opponent);
        layoutCopy[myPlayer.getX()][myPlayer.getY()] = myPlayer;
        return layoutCopy;
    }

    public void setLayout(char[][] layoutUpdate, Player myPlayer, List<Player> opponents) {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                switch (layoutUpdate[x][y]) {
                    case 'i' -> setFieldAt(x, y, new IndestructibleBlock());
                    case 'd' -> setFieldAt(x, y, new Bomb(x, y));
                    default -> setFieldAt(x, y, new Free());
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
        Field[][] layoutCopy = new Field[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                layoutCopy[x][y] = getFieldAt(x, y);
            }
        }
        return layoutCopy;
    }

    private Field getFieldAt(int x, int y) {
        isValidCoordinate(x, y);
        return layout[x][y];
    }

    private void setFieldAt(int x, int y, Field field) {
        isValidCoordinate(x, y);
        layout[x][y] = field;
    }

    private void isValidCoordinate(int x, int y) {
        if (isNotValidCoordinate(x, y)) {
            throw new ArrayIndexOutOfBoundsException("Invalid Coordinate x:" + x + " y:" + y);
        }
    }

    private boolean isNotValidCoordinate(int x, int y) {
        return x < 0 || x >= height
                || y < 0 || y >= width;
    }
}
