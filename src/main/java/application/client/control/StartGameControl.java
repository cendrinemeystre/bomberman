package application.client.control;

import application.client.model.Game;
import application.client.model.Labyrinth;
import application.client.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.StartGame;

public class StartGameControl extends Control{
    public StartGameControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void startGame(StartGame message) {
        // TODO start the game and render the initial map with all the players and the labyrinth
        view.displayMessage("Spiel gestartet");
        char[][] initialGameState = {
                {'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', ' ', 'W', ' ', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W'}
        };
        Labyrinth labyrinth = new Labyrinth(5, 5, initialGameState);
        view.updateLabyrinth(labyrinth);
    }
}
