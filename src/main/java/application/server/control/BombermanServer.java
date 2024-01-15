package application.server.control;

import application.server.labyrinth.Labyrinth;
import application.server.model.Game;
import application.server.network.MessageEntry;
import application.server.network.MessageQueue;
import network.server.Server;
import network.server.ServerImplementation;

public class BombermanServer {

    private BombermanServer() {
        MessageQueue queue = new MessageQueue();
        MessageEntry entry = new MessageEntry(queue);
        Server server = new ServerImplementation(entry);
        Labyrinth labyrinth = new Labyrinth();
        Game game = new Game(labyrinth);
        ControllerFactory controllerFactory = new ControllerFactory(server, game);
        Dispatcher dispatcher = new Dispatcher(queue, controllerFactory);
        dispatcher.start();
    }

    public static void main(String[] args) {
        new BombermanServer();
    }
}
