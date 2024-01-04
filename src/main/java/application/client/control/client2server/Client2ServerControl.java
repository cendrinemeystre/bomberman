package application.client.control.client2server;

import application.client.control.Control;
import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;

public abstract class Client2ServerControl extends Control {
    public Client2ServerControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }
}
