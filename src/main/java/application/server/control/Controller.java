package application.server.control;

import application.server.model.Game;
import network.server.Server;
import protocol.client2server.ClientMessage;

public abstract class Controller {
    Server server;
    Game game;

    protected Controller(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public abstract void handleMessage(ClientMessage message, String connectionId);

}
