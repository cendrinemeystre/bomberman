package application.server.control;

import java.io.File;
import java.nio.file.Path;

import application.server.labyrinth.Labyrinth;
import application.server.labyrinth.loader.SaveState;
import application.server.labyrinth.loader.SaveStateManager;
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
        Labyrinth labyrinth = loadLabyrint(2);
        Game game = new Game(labyrinth);
        ControllerFactory controllerFactory = new ControllerFactory(server, game);
        Dispatcher dispatcher = new Dispatcher(queue, controllerFactory);
        dispatcher.start();
    }

    public static Labyrinth loadLabyrint(int version) {
        Path path = Path.of("src/main/resources/labyrinthLibrary/lab" + version + ".json");
        Labyrinth labyrinth = new Labyrinth();
        try {
            File file = path.toFile();
            SaveStateManager saveStateManager = new SaveStateManager(file);
            SaveState saveState = saveStateManager.read();
            labyrinth = saveState.getLabyrinth();
            return labyrinth;
        } catch (Exception e) {
            System.out.println("file does not exist");
            System.out.println(path);
            e.printStackTrace();
        }
        return labyrinth;
    }

    public static void main(String[] args) {
        new BombermanServer();
    }
}
