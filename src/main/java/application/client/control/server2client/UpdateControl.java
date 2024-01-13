package application.client.control.server2client;

import application.client.model.Game;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.Update;

public class UpdateControl extends ServerToClientControl<Update> {
    public UpdateControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    @Override
    public void handleMessage(Update message) {
        game.setLabyrinthLayout(message.getMap());
        view.update();
    }
}
