package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.PlayerMoved;

public class PlayerMovedControl extends Server2ClientControl<PlayerMoved> {
    public PlayerMovedControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(PlayerMoved message) {
        game.playerMoved(message.getPlayerName(), message.getDirection());
        view.update();
    }
}
