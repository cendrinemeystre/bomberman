package application.client.control.client2server;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.client2server.DropBomb;

public class DropBombControl extends Client2ServerControl {
    public DropBombControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void dropBomb(String playerName, int positionX, int positionY) {
        DropBomb dropBomb = new DropBomb(playerName, positionX, positionY);
        serverProxy.send(dropBomb);
    }
}
