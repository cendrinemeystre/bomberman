package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;

public class Control {
    Game game;
    ServerProxy serverProxy;
    BombermanPanel view;

    public Control(ServerProxy serverProxy, Game game, BombermanPanel view) {
        this.serverProxy = serverProxy;
        this.game = game;
        this.view = view;
    }
}
