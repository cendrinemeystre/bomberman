package application.client.model;

import protocol.server2client.PlayerJoined;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player myPlayer;
    private List<Player> oponents = new ArrayList<>();

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        if (myPlayer.isYourName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            Player oponent = new Player(playerName, initialX, initialY);
            oponents.add(oponent);
        }
    }
}
