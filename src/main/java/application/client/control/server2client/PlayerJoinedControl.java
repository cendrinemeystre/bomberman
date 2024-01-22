package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.PlayerJoined;

public class PlayerJoinedControl extends ServerToClientControl<PlayerJoined> {
    public PlayerJoinedControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(PlayerJoined message) {
        if (game.isMyPlayer(message)) {
            view.loggedIn();
        }
        if (game.isNotOpponent(message)) {
            game.playerJoined(message);
            view.displayMessage(message.getPlayerName() + " hat sich angemeldet.");
        }
    }
}
