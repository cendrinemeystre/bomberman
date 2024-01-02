package application.client.view.panel;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends Panel<BorderLayout> {
    private JTextArea messageTextArea = new JTextArea();

    public MessagePanel() {
        super(new BorderLayout());
    }

    @Override
    public JPanel createPanel() {
        messageTextArea = new JTextArea();
        messageTextArea.setRows(20);
        messageTextArea.setEditable(false);
        addToPanel(messageTextArea, null);
        return getPanel();
    }

    public void displayMessage(String message) {
        messageTextArea.append(message + "\n");
    }
}
