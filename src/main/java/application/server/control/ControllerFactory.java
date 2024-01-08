package application.server.control;

import application.server.model.Game;
import network.server.Server;

public class ControllerFactory {
    private Server server;
    private Game game;

    public ControllerFactory(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public Controller createJoinGameController() {
        return new JoinGameController(server, game);
    }

    public Controller createMovePlayerController() {
        return new MovePlayerController(server, game);
    }

    public Controller createDropBombController() {
        return new DropBombController(server, game);
    }
}
