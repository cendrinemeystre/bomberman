package application.client.view.panel;

import application.client.control.ControlFactory;
import application.client.control.client2server.JoinGameControl;

import javax.swing.*;
import java.awt.*;


public class LoginPanel extends Panel<BorderLayout> {
    private JTextField playerNameTextField;

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
        playerNameTextField = new JTextField();
        playerNameTextField.setPreferredSize(new Dimension(200, 50));
        return playerNameTextField;
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Anmelden");
        button.addActionListener(e -> {
            JoinGameControl control = ControlFactory.instance().createClient2ServerControl(JoinGameControl.class);
            control.joinGame(playerNameTextField.getText());
            playerNameTextField.setEnabled(false);
        });
        return button;
    }

}
