package application.server.control;

import application.server.model.Game;
import network.server.Server;
import protocol.client2server.ClientMessage;
import protocol.client2server.MovePlayer;
import protocol.server2client.ErrorMessage;
import protocol.server2client.PlayerMoved;

public class MovePlayerController extends Controller {
    public MovePlayerController(Server server, Game game) {
        super(server, game);
    }

    @Override
    public void handleMessage(ClientMessage message, String connectionId) {
        MovePlayer playerMessage = (MovePlayer) message;
        if (game.isMovePossible(message.getPlayerName(), playerMessage.getDirection())) {
            game.movePlayer(message.getPlayerName(), playerMessage.getDirection());
            server.broadcast(new PlayerMoved(message.getPlayerName(), playerMessage.getDirection()));
        } else {
            server.send(new ErrorMessage("You can't go that way"), connectionId);
        }
    }
}
