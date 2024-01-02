package application.client.view;

import application.client.model.Labyrinth;
import application.client.view.panel.LabyrinthPanel;
import application.client.view.panel.LoginPanel;
import application.client.view.panel.MessagePanel;

import javax.swing.*;
import java.awt.*;

public class BombermanPanel extends JPanel {
    private final LoginPanel loginPanel;
    private final LabyrinthPanel labyrinthPanel;
    private final MessagePanel messagePanel;

    public BombermanPanel() {
        setLayout(new BorderLayout());
        setFocusable(true);
        // Fill panel
        loginPanel = new LoginPanel();
        add(loginPanel.createPanel(), BorderLayout.NORTH);
        labyrinthPanel = new LabyrinthPanel();
        add(labyrinthPanel.createPanel(), BorderLayout.CENTER);
        messagePanel = new MessagePanel();
        add(messagePanel.createPanel(), BorderLayout.SOUTH);
    }

    public void updateLabyrinth(Labyrinth labyrinth) {
        labyrinthPanel.updateGameState(labyrinth);
    }

    public void displayMessage(String message) {
        messagePanel.displayMessage(message);
    }

}
