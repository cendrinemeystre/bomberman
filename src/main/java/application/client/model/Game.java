package application.client.model;

import protocol.server2client.PlayerJoined;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player myPlayer;
    private List<Player> opponents = new ArrayList<>();

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        if (myPlayer.isName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            Player opponent = new Player(playerName, initialX, initialY);
            opponents.add(opponent);
        }
    }
}
