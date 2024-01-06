package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.StartGame;

public class StartGameControl extends Server2ClientControl<StartGame> {
    public StartGameControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(StartGame message) {
        view.displayMessage("Spiel gestartet");
        char[][] map = message.getMap();
        game.initializeLabyrinth(map.length, map[0].length);
        game.setLabyrinthLayout(map);
        view.startGame(game);
    }
}
