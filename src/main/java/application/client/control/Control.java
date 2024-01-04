package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;

public abstract class Control {
    protected final Game game;
    protected final ServerProxy serverProxy;
    protected final BombermanPanel view;

    public Control(ServerProxy serverProxy, Game game, BombermanPanel view) {
        this.serverProxy = serverProxy;
        this.game = game;
        this.view = view;
    }

}
