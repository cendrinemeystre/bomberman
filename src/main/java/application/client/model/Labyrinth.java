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
    private final Map<String, Bomb> bombList;
    private Field[][] layout;

    public Labyrinth(int height, int width) {
        bombList = new HashMap<>();
        layout = new Field[height][width];
    }

    public Field[][] getLayout() {
        return layout;
    }

    public void setLayout(char[][] layoutUpdate, Player myPlayer, List<Player> opponents) {
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                switch (layoutUpdate[i][j]) {
                    case 'i' -> layout[i][j] = new IndestructibleBlock();
                    case 'd' -> layout[i][j] = new DestructibleBlock();
                    case 'b' -> layout[i][j] = new Bomb(i, j);
                    case 'f' -> layout[i][j] = new Free();
                }
                if (isPosition(myPlayer, i, j)) {
                    layout[i][j] = new Player(myPlayer.getName());
                } else {
                    for (Player opponent : opponents) {
                        if (isPosition(opponent, i, j)) {
                            layout[i][j] = new Player(opponent.getName(), opponent.getX(), opponent.getY(), opponent.getType());
                        }
                    }
                }
            }
        }
    }

    private boolean isPosition(Player player, int i, int j) {
        return i == player.getX() && j == player.getY();
    }

    public void movePlayerTo(int fromX, int fromY, int toX, int toY) {
        Field from = layout[fromX][fromY];
        layout[fromX][fromY] = new Free();
        layout[toX][toY] = from;
    }

    public void removePlayer(int positionX, int positionY) {
        layout[positionX][positionY] = new Free();
    }

    public Bomb getBomb(int positionX, int positionY) {
        return this.bombList.values().stream()
                .filter(bomb -> bomb.getPositionX() == positionX && bomb.getPositionY() == positionY)
                .findFirst()
                .orElse(null);
    }

    public void addBomb(String id, int x, int y) {
        Bomb bomb = new Bomb(x, y);
        this.bombList.put(id, bomb);
        layout[x][y] = bomb;
    }

    public void removeBomb(String id) {
        Bomb bomb = this.bombList.get(id);
        layout[bomb.getPositionX()][bomb.getPositionY()] = new Free();
        this.bombList.remove(id);
    }
}
