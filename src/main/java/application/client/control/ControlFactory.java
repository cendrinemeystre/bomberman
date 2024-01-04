package application.client.control;

import application.client.control.client2server.Client2ServerControl;
import application.client.control.server2client.Server2ClientControl;
import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;

import java.lang.reflect.Constructor;

public class ControlFactory {
    private static ControlFactory instance;
    private final ServerProxy serverProxy;
    private final Game game;
    private final BombermanPanel view;

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

    public <T extends Client2ServerControl> T createClient2ServerControl(Class<T> controlClass) {
        try {
            Constructor<T> constructor = controlClass.getConstructor(ServerProxy.class, Game.class, BombermanPanel.class);
            return constructor.newInstance(serverProxy, game, view);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends Server2ClientControl> T createServer2ClientControl(Class<T> controlClass) {
        try {
            Constructor<T> constructor = controlClass.getConstructor(ServerProxy.class, Game.class, BombermanPanel.class);
            return constructor.newInstance(serverProxy, game, view);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
