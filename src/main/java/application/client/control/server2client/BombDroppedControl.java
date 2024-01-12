package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.BombDropped;

public class BombDroppedControl extends Server2ClientControl<BombDropped> {
    public BombDroppedControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(BombDropped message) {
        game.bombDropped(message.getId(), message.getPositionX(), message.getPositionY());
        view.update();
    }
}
