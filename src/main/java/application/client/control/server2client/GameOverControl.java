package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.GameOver;

public class GameOverControl extends Server2ClientControl<GameOver> {
    public GameOverControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(GameOver message) {
        game.gameOver();
        view.gameOver(message.getWinnerName(), message.getHighscoreList());
    }
}
