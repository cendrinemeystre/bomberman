package application.client.model;

import java.util.HashMap;
import java.util.Map;

public class Labyrinth {
    private char[][] layout;

    private final Map<String, Bomb> bombList;

    public Labyrinth() {
        bombList = new HashMap<>();
    }

    public char[][] getLayout() {
        return layout;
    }

    public void setLayout(char[][] layout) {
        this.layout = layout;
    }

    public void movePlayerTo(int fromX, int fromY, int toX, int toY) {
        char from = layout[fromX][fromY];
        layout[fromX][fromY] = 'f';
        layout[toX][toY] = from;
    }

    public void removePlayer(int positionX, int positionY) {
        layout[positionX][positionY] = 'f';
    }

    public Bomb getBomb(int positionX, int positionY) {
        return this.bombList.values().stream()
                .filter(bomb -> bomb.positionX() == positionX && bomb.positionY() == positionY)
                .findFirst()
                .orElse(null);
    }

    public void addBomb(String id, Bomb bomb) {
        this.bombList.put(id, bomb);
        layout[bomb.positionX()][bomb.positionY()] = 'b';
    }

    public void removeBomb(String id) {
        Bomb bomb = this.bombList.get(id);
        layout[bomb.positionX()][bomb.positionY()] = 'f';
        this.bombList.remove(id);
    }
}
