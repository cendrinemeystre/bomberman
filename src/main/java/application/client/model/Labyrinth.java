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
    private Player myPlayer;
    private List<Player> opponents;


    public Labyrinth(int height, int width) {
        bombMap = new HashMap<>();
        layout = new Field[height][width];
    }

    public Field[][] getLayoutForRendering() {
        Field[][] layoutCopy = new Field[layout.length][layout[0].length];
        for (int x = 0; x < layout.length; x++) {
            System.arraycopy(layout[x], 0, layoutCopy[x], 0, layout[x].length);
        }
        bombMap.values()
                .forEach(bomb -> layoutCopy[bomb.getPositionX()][bomb.getPositionY()] = bomb);
        opponents.forEach(opponent -> layoutCopy[opponent.getX()][opponent.getY()] = opponent);
        layoutCopy[myPlayer.getX()][myPlayer.getY()] = myPlayer;
        return layoutCopy;
    }

    public void setLayout(char[][] layoutUpdate, Player myPlayer, List<Player> opponents) {
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                switch (layoutUpdate[i][j]) {
                    case 'i' -> layout[i][j] = new IndestructibleBlock();
                    case 'd' -> layout[i][j] = new DestructibleBlock();
                    default -> layout[i][j] = new Free();
                }
            }
        }
        this.myPlayer = myPlayer;
        this.opponents = opponents;
    }

    public void removePlayer(int positionX, int positionY) {
        layout[positionX][positionY] = new Free();
    }

    public void addBomb(String id, int x, int y) {
        Bomb bomb = new Bomb(x, y);
        this.bombMap.put(id, bomb);
    }

    public void removeBomb(String id) {
        this.bombMap.remove(id);
    }
}
