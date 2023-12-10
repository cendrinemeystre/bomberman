package application.client.control;

import network.Message;
import network.client.ClientApplicationInterface;
import protocol.server2client.*;

public class Dispatcher implements ClientApplicationInterface {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof ErrorMessage) {
            
        } else if (message instanceof GameOver) {
            
        } else if (message instanceof PlayerHit) {

        } else if (message instanceof PlayerJoined) {
            PlayerJoinedControl control = ControlFactory.instance().createPlayerJoinedControl();
            control.playerJoined((PlayerJoined)message);
        } else if (message instanceof  PlayerMoved) {

        } else if (message instanceof StartGame) {

        } else if (message instanceof Update) {

        }
    }
}
