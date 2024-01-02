package application.client.view.panel;

import application.client.model.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LabyrinthPanel extends Panel<BorderLayout> {
    private Labyrinth labyrinth;
    private Map<Character, ImageIcon> imageMap = new HashMap<>();
    private static final int CELL_SIZE = 30;
    private static final String filePath = "/bomberman/field/";
    private ImageIcon unbreakableBlock;
    private ImageIcon emptyImage;
    private ImageIcon breakableBlock;

    public LabyrinthPanel() {
        super(new BorderLayout());
        loadImages();
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = getPanel();
        panel.setFocusable(true);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key: " + e.getKeyChar());
                repaint();
            }
        });

        return panel;
    }

    private void loadImages() {
        unbreakableBlock = new ImageIcon(Objects.requireNonNull(getClass().getResource(filePath + "UNBREAKABLE_BLOCK.png")));
        breakableBlock = new ImageIcon(Objects.requireNonNull(getClass().getResource(filePath + "BREAKABLE_BLOCK.png")));
        emptyImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(filePath + "FREE.png")));

        // Add images to the map for easy access
        imageMap.put('W', unbreakableBlock);
        imageMap.put(' ', emptyImage);
        imageMap.put('P', breakableBlock);
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("paint");
        if (labyrinth != null) {
            for (int row = 0; row < labyrinth.getHeight(); row++) {
                for (int col = 0; col < labyrinth.getWidth(); col++) {
                    int x = col * CELL_SIZE;
                    int y = row * CELL_SIZE;

                    char currentElement = labyrinth.getLayout()[row][col];

                    // Check if the character has a corresponding image
                    if (imageMap.containsKey(currentElement)) {
                        ImageIcon image = imageMap.get(currentElement);
                        g.drawImage(image.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
                    }
                }
            }
        }
    }

    public void updateGameState(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        getPanel().repaint();
    }
}
