package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.PlayerJoined;

public class PlayerJoinedControl extends Control {
    public PlayerJoinedControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void playerJoined(PlayerJoined message) {
        game.playerJoined(message);
        view.displayMessage(message.getPlayerName() + " hat sich angemeldet.");
    }
}
