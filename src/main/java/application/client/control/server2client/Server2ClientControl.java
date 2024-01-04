package application.client.control.server2client;

import application.client.control.Control;
import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.Message;
import network.client.ServerProxy;

public abstract class Server2ClientControl<T extends Message> extends Control {
    public Server2ClientControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public abstract void handleMessage(T message);
}
