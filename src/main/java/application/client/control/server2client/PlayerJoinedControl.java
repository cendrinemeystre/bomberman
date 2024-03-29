package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.PlayerJoined;

public class PlayerJoinedControl extends Server2ClientControl<PlayerJoined> {
    public PlayerJoinedControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(PlayerJoined message) {
        game.playerJoined(message);
        view.displayMessage(message.getPlayerName() + " hat sich angemeldet.");
    }
}
