package application.client.view.panel.bottom;

import application.client.view.panel.Panel;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends Panel<BorderLayout> {
    private final JTextArea messageTextArea;

    public MessagePanel() {
        super(new BorderLayout());
        messageTextArea = new JTextArea();
    }

    @Override
    public JPanel createPanel() {
        messageTextArea.setRows(20);
        messageTextArea.setEditable(false);
        addToPanel(messageTextArea, null);
        JPanel panel = getPanel();
        panel.setEnabled(false);
        return panel;
    }

    public void displayMessage(String message) {
        messageTextArea.append(message + "\n");
    }
}
