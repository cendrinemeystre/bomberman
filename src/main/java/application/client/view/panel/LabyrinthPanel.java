package application.client.view.panel;

import application.client.model.Game;
import application.client.model.field.Field;
import application.client.model.field.FieldType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LabyrinthPanel extends Panel<GridBagLayout> {
    private final Map<Character, ImageIcon> imageMap = new HashMap<>();
    private Game game;

    public LabyrinthPanel() {
        super(new GridBagLayout());
        // TODO remove the next line only for testing
        game = new Game();
        loadImages();
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = getPanel();
        panel.setFocusable(true);
        return panel;
    }

    public void updateGameMap() {
        printLabyrinth();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void printLabyrinth() {
        getPanel().removeAll();
        Field[][] layout = game.getLabyrinth().getLayoutForRendering();
        GridBagConstraints gridBagConstraints = getGridBagConstraints();
        if (layout != null) {
            for (Field[] fields : layout) {
                JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                for (Field currentElement : fields) {
                    char key = currentElement.getType().getKey();
                    if (imageMap.containsKey(key)) {
                        ImageIcon image = imageMap.get(key);
                        JLabel label = new JLabel(image);
                        tempPanel.add(label);
                    }
                }
                addToPanel(tempPanel, gridBagConstraints);
            }
        }
        getPanel().validate();
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
