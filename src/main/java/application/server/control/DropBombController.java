package application.server.control;

import application.server.model.Game;
import network.server.Server;
import protocol.client2server.ClientMessage;
import protocol.client2server.DropBomb;
import protocol.server2client.BombDropped;
import protocol.server2client.BombExploded;
import protocol.server2client.GameOver;
import protocol.server2client.PlayerHit;
import protocol.server2client.Update;

import java.util.List;

public class DropBombController extends Controller {

    public DropBombController(Server server, Game game) {
        super(server, game);
    }

    @Override
    public void handleMessage(ClientMessage message, String connectionId) {
        DropBomb dropBombMessage = (DropBomb) message;
        String bombId = game.dropBomb(dropBombMessage.getPositionX(), dropBombMessage.getPositionY());
        server.broadcast(new BombDropped(bombId, dropBombMessage.getPositionX(), dropBombMessage.getPositionY()));
        if (game.bombTimer(game.getBombById(bombId))) {
            explodeBomb(bombId, dropBombMessage);
        }
    }

    private void explodeBomb(String bombId, DropBomb dropBombMessage) {
        server.broadcast(new BombExploded(bombId));
        List<String> hitPlayers = game.checkPlayerHit(game.getBombById(bombId));
        for (String playerName : hitPlayers) {
            server.broadcast(new PlayerHit(playerName));
            game.logScore(dropBombMessage.getPlayerName());
            game.killPlayer(playerName);
        }
        game.updateLabyrinth(game.getBombById(bombId));
        server.broadcast(new Update(game.getLabyrinth().getCharMap()));
        if (!game.checkIfRunning()) {
            // TODO give the real winnerName as input
            server.broadcast(new GameOver("winner", game.getScoreboard()));
        }
    }
}
