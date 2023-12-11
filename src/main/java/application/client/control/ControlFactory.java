package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;

public class ControlFactory {
    private static ControlFactory instance;
    private ServerProxy serverProxy;
    private Game game;
    private BombermanPanel view;

    private ControlFactory(ServerProxy serverProxy, Game game, BombermanPanel view) {
        this.serverProxy = serverProxy;
        this.game = game;
        this.view = view;
    }
    public static ControlFactory instance() {
        return instance;
    }

    public static void instantiate(ServerProxy serverProxy, Game game, BombermanPanel view) {
        if (instance == null) {
            instance = new ControlFactory(serverProxy, game, view);
        }
    }

    public JoinGameControl createJoinGameControl() {
        return new JoinGameControl(serverProxy, game, view);
    }

    public PlayerJoinedControl createPlayerJoinedControl() {
        return new PlayerJoinedControl(serverProxy, game, view);
    }

    public ErrorMessageControl createErrorMessageControl() {
        return new ErrorMessageControl(serverProxy, game, view);
    }

    public StartGameControl createStartGameControl() {
        return new StartGameControl(serverProxy, game, view);
    }
}
