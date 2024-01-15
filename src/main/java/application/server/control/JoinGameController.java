package application.server.control;

import application.server.model.Game;
import application.server.model.Player;
import network.Message;
import network.server.Server;
import protocol.client2server.ClientMessage;
import protocol.server2client.ErrorMessage;
import protocol.server2client.StartGame;

public class JoinGameController extends Controller {

    public JoinGameController(Server server, Game game) {
        super(server, game);
    }

    @Override
    public void handleMessage(ClientMessage message, String connectionId) {
        if (game.numberOfPlayersComplete()) {
            sendErrorMessage("Spiel läuft bereits", connectionId);
        } else if (!game.isUnique(message.getPlayerName())) {
            sendErrorMessage("Spielername ist bereits vergeben", connectionId);
        } else {
            joinGame(message, connectionId);
        }
    }

    private void joinGame(ClientMessage message, String connectionId) {
        Player player = game.createPlayer(message.getPlayerName(), connectionId);
        Message response = player.createPlayerJoined();
        server.broadcast(response);
        if (game.numberOfPlayersComplete()) {
            server.broadcast(new StartGame(game.getLabyrinth().getCharMap()));
        }
    }
}
