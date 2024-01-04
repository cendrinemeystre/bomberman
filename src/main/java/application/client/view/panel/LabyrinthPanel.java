package application.client.view.panel;

import application.client.model.Bomb;
import application.client.model.FieldType;
import application.client.model.Game;
import application.client.model.Labyrinth;
import protocol.Direction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LabyrinthPanel extends Panel<GridBagLayout> {
    private Game game;
    private final Map<Character, ImageIcon> imageMap = new HashMap<>();

    public LabyrinthPanel() {
        super(new GridBagLayout());
        game = new Game();
        loadImages();

//        char[][] initialGameState = {
//                {'i', 'i', 'i', 'i', 'i'},
//                {'i', '2', 'f', 'f', 'i'},
//                {'i', 'f', 'i', '3', 'i'},
//                {'1', 'b', 'f', 'f', 'i'},
//                {'i', 'i', 'i', 'i', 'i'}};
//        Labyrinth labyrinth = new Labyrinth();
//        labyrinth.setLayout(initialGameState);
//        game.setLabyrinth(labyrinth);

    }

    @Override
    public JPanel createPanel() {
        JPanel panel = getPanel();
        panel.setFocusable(true);
        return panel;
    }

    public void updateGameMap(char[][] map) {
        game.getLabyrinth().setLayout(map);
        printLabyrinth();
    }

    public void playerMoved(String playerName, Direction direction) {
        game.playerMoved(playerName, direction);
        printLabyrinth();
    }

    public void bombDropped(String id, int positionX, int positionY) {
        game.getLabyrinth().addBomb(id, new Bomb(positionX, positionY));
        printLabyrinth();
    }

    public void bombExploded(String id) {
        game.getLabyrinth().removeBomb(id);

    }

    public void playerHit(String playerName) {
        game.playerHit(playerName);
        printLabyrinth();
    }

    public void setGame(Game game) {
        this.game = game;
        game.setLabyrinth(new Labyrinth());
    }

    private void printLabyrinth() {
        char[][] layout = game.getLabyrinth().getLayout();
        GridBagConstraints gridBagConstraints = getGridBagConstraints();
        if (layout != null) {
            for (char[] chars : layout) {
                JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                for (char currentElement : chars) {
                    if (imageMap.containsKey(currentElement)) {
                        ImageIcon image = imageMap.get(currentElement);
                        JLabel label = new JLabel(image);
                        tempPanel.add(label);
                    }
                }
                addToPanel(tempPanel, gridBagConstraints);
            }
        }
        getPanel().repaint();
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        return gridBagConstraints;
    }

    private void loadImages() {
        for (FieldType fieldType : FieldType.values()) {
            imageMap.put(fieldType.getKey(), createImageIcon(fieldType.name()));
        }
    }

    private ImageIcon createImageIcon(String imageName) {
        return new ImageIcon(getPicture(imageName));
    }

    private URL getPicture(String image) {
        return Objects.requireNonNull(getClass().getResource("/bomberman/field/" + image + ".png"));
    }
}
