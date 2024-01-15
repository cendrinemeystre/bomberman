package application.server.labyrinth;

import application.server.labyrinth.tile.Tile;
import application.server.model.Bomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static application.server.labyrinth.tile.TileOccupation.BOMB;
import static application.server.labyrinth.tile.TileType.FREE;

public class Labyrinth {

    private int width;

    private int height;

    private Tile[][] tiles;

    public Labyrinth() {
    }

    public Labyrinth(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        setTiles(width, height);
    }

    private void setTiles(int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.tiles[x][y] = new Tile(x, y);
            }
        }
    }

    private List<Tile> getAllTiles() {

        List<Tile> tileList = new ArrayList<>();
        for (Tile[] tile : tiles) {
            tileList.addAll(Arrays.asList(tile).subList(0, tiles[0].length));
        }

        return tileList;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTile(int x, int y) {
        return tiles[x - 1][y - 1];
    }

    public char[][] getCharMap() {
        char[][] map = new char[tiles.length][tiles[0].length];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                map[x][y] = tiles[x][y].getType().value;
            }
        }
        return map;
    }

    public boolean isTileEmpty(int x, int y) {
        if (tiles.length > x && tiles[0].length > y && x >= 0 && y >= 0) {
            return tiles[x][y].isEmpty();
        } else
            return false;
    }

    public void dropBomb(int x, int y) {
        tiles[x][y].setOccupation(BOMB);
    }

    public void bombExploded(Bomb bomb) {
        tiles[bomb.getX()][bomb.getY()].setOccupation(null);
        tiles[bomb.getX()][bomb.getY()].setType(FREE);
        for (Tile tile : getAllTiles()) {
            if (bomb.checkIfHit(tile.getX(), tile.getY())) {
                tile.hit();
            }
        }
    }
}
