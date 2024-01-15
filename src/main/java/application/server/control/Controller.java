package application.server.control;

import application.server.model.Game;
import network.server.Server;
import protocol.client2server.ClientMessage;
import protocol.server2client.ErrorMessage;

public abstract class Controller {
    Server server;
    Game game;

    protected Controller(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public abstract void handleMessage(ClientMessage message, String connectionId);

    protected void sendErrorMessage(String errorMessage, String connectionId) {
        server.send(new ErrorMessage(errorMessage), connectionId);
    }
}
