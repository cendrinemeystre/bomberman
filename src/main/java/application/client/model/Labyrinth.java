package application.client.model;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private final int width;
    private final int height;
    private char[][] layout;
    private List<Labyrinth> labyrinthList = new ArrayList<>();

    public Labyrinth(int width, int height, char[][] layout) {
        this.width = width;
        this.height = height;
        this.layout = layout;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getLayout() {
        return layout;
    }

    public void setLayout(char[][] layout) {
        this.layout = layout;
    }
}
