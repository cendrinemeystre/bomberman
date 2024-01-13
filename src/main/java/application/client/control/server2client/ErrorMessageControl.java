package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.ErrorMessage;

public class ErrorMessageControl extends ServerToClientControl<ErrorMessage> {
    public ErrorMessageControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(ErrorMessage message) {
        view.displayMessage(message.getErrorMessage());
    }
}
