package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.PlayerHit;

public class PlayerHitControl extends Server2ClientControl<PlayerHit> {
    public PlayerHitControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(PlayerHit message) {
        game.playerHit(message.getPlayerName());
        view.update();
    }
}
