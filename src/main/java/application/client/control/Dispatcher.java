package application.client.control;

import application.client.control.server2client.BombDroppedControl;
import application.client.control.server2client.BombExplodedControl;
import application.client.control.server2client.ErrorMessageControl;
import application.client.control.server2client.GameOverControl;
import application.client.control.server2client.PlayerHitControl;
import application.client.control.server2client.PlayerJoinedControl;
import application.client.control.server2client.PlayerMovedControl;
import application.client.control.server2client.ServerToClientControl;
import application.client.control.server2client.StartGameControl;
import application.client.control.server2client.UpdateControl;
import network.Message;
import network.client.ClientApplicationInterface;
import protocol.server2client.BombDropped;
import protocol.server2client.BombExploded;
import protocol.server2client.ErrorMessage;
import protocol.server2client.GameOver;
import protocol.server2client.PlayerHit;
import protocol.server2client.PlayerJoined;
import protocol.server2client.PlayerMoved;
import protocol.server2client.StartGame;
import protocol.server2client.Update;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher implements ClientApplicationInterface {
    private final Map<Class<? extends Message>, Class<? extends ServerToClientControl>> controlMap;

    public Dispatcher() {
        controlMap = new HashMap<>();
        initializeControlMap();
    }

    @Override
    public void handleMessage(Message message) {
        Class<? extends Message> messageType = message.getClass();
        if (controlMap.containsKey(messageType)) {
            Class<? extends ServerToClientControl> controlClass = controlMap.get(messageType);
            ServerToClientControl control = ControlFactory.instance().createServer2ClientControl(controlClass);
            control.handleMessage(message);
        } else {
            // Handle unknown message type
        }
    }

    private void initializeControlMap() {
        controlMap.put(PlayerJoined.class, PlayerJoinedControl.class);
        controlMap.put(ErrorMessage.class, ErrorMessageControl.class);
        controlMap.put(StartGame.class, StartGameControl.class);
        controlMap.put(GameOver.class, GameOverControl.class);
        controlMap.put(Update.class, UpdateControl.class);
        controlMap.put(PlayerMoved.class, PlayerMovedControl.class);
        controlMap.put(BombDropped.class, BombDroppedControl.class);
        controlMap.put(BombExploded.class, BombExplodedControl.class);
        controlMap.put(PlayerHit.class, PlayerHitControl.class);
    }
}
