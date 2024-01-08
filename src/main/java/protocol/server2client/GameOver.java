package protocol.server2client;

import network.Message;

public class GameOver implements Message {
    private String[] highscoreList;

    public GameOver(String[] highscoreList) {
        this.highscoreList = highscoreList;
    }

    public String[] getHighscoreList() {
        return highscoreList;
    }
}
