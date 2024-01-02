package application.client.view.panel;

import application.client.control.ControlFactory;
import application.client.control.JoinGameControl;

import javax.swing.*;
import java.awt.*;


public class LoginPanel extends Panel<BorderLayout> {

    public LoginPanel() {
        super(new BorderLayout());
    }

    @Override
    public JPanel createPanel() {
        addToPanel(createPlayerNameTextField(), BorderLayout.WEST);
        addToPanel(createLoginButton(), BorderLayout.EAST);
        return getPanel();
    }

    private JTextField createPlayerNameTextField() {
        JTextField playerNameTextField = new JTextField();
        playerNameTextField.setPreferredSize(new Dimension(200, 50));
        playerNameTextField.addActionListener(e -> {
            JoinGameControl control = ControlFactory.instance().createJoinGameControl();
            control.joinGame(playerNameTextField.getText());
            playerNameTextField.setEnabled(false);
        });
        return playerNameTextField;
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Anmelden");
        button.addActionListener(e -> {

        });
        return button;
    }

}
