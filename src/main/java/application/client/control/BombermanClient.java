package application.client.control;

import application.client.model.Game;
import application.client.view.BombermanFrame;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import network.client.gruppe2.ServerProxyStub;


public class BombermanClient {
    private BombermanClient() {
        Game game = new Game();
        Dispatcher dispatcher = new Dispatcher();
        ServerProxy serverProxy = new ServerProxyStub(dispatcher);
        BombermanPanel panel = new BombermanPanel();
        new BombermanFrame(panel);
        ControlFactory.instantiate(serverProxy, game, panel);
        panel.startGame(game);
    }

    public static void main(String[] args) {
        new BombermanClient();
    }
}
