package application.client.view;

import application.client.control.ControlFactory;
import application.client.control.JoinGameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BombermanPanel extends JPanel {
    private JTextField playerNameTextField = new JTextField();
    private JTextArea messageTextArea = new JTextArea();
    public BombermanPanel() {
        setLayout(new BorderLayout());
        playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JoinGameControl control = ControlFactory.instance().createJoinGameControl();
                control.joinGame(playerNameTextField.getText());
                playerNameTextField.setEnabled(false);
            }
        });
        add(playerNameTextField, BorderLayout.NORTH);
        messageTextArea.setRows(4);
        messageTextArea.setEditable(false);
        add(messageTextArea, BorderLayout.SOUTH);
    }

    public void displayMessage(String message) {
        messageTextArea.append(message + "\n");
    }
}
