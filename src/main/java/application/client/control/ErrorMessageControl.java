package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.ErrorMessage;

public class ErrorMessageControl extends Control {
    public ErrorMessageControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void errorMessage(ErrorMessage message) {
        view.displayMessage(message.getErrorMessage() );
    }
}
