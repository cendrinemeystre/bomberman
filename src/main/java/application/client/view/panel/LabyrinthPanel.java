package application.client.view.panel;

import application.client.model.Game;
import application.client.model.field.Field;
import application.client.view.helper.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class LabyrinthPanel extends Panel<GridBagLayout> {
    private final ImageLoader imageLoader;
    private Game game;

    public LabyrinthPanel() {
        super(new GridBagLayout());
        imageLoader = new ImageLoader();
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
        JPanel panel = getPanel();
        panel.removeAll();
        for (Field[] fields : game.getLabyrinthLayoutForRendering()) {
            createLineLayout(fields);
        }
        panel.validate();
        panel.repaint();
    }

    private void createLineLayout(Field[] fields) {
        JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        for (Field currentElement : fields) {
            addLabelForKeyToPanel(currentElement.getType().getKey(), tempPanel);
        }
        addToPanel(tempPanel, getGridBagConstraints());
    }

    private void addLabelForKeyToPanel(char key, JPanel tempPanel) {
        ImageIcon image = imageLoader.getImageByKey(key);
        tempPanel.add(new JLabel(image));
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        return gridBagConstraints;
    }
}
