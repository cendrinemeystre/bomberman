package application.client.control.client2server;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.Direction;
import protocol.client2server.MovePlayer;

public class MovePlayerControl extends Client2ServerControl {
    public MovePlayerControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void movePlayer(String playerName, Direction direction) {
        MovePlayer movePlayer = new MovePlayer(playerName, direction);
        serverProxy.send(movePlayer);
    }
}
