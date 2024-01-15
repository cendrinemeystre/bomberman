package application.client.view.panel.bottom;

import application.client.view.panel.Panel;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends Panel<BorderLayout> {
    private final JScrollPane messageScrollPane;
    private final JTextArea messageTextArea;

    public MessagePanel() {
        super(new BorderLayout());
        messageTextArea = new JTextArea();
        messageScrollPane = new JScrollPane(messageTextArea);
    }

    @Override
    public JPanel createPanel() {
        messageTextArea.setRows(20);
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        addToPanel(messageScrollPane, null);
        JPanel panel = getPanel();
        panel.setEnabled(false);
        return panel;
    }

    public void displayMessage(String message) {
        messageTextArea.append(message + "\n");
    }
}
