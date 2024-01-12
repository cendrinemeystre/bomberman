package application.client.view.adapter;

import application.client.control.ControlFactory;
import application.client.control.client2server.DropBombControl;
import application.client.control.client2server.MovePlayerControl;
import application.client.model.field.Player;
import protocol.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BombermanKeyAdapter extends KeyAdapter {
    private final MovePlayerControl movePlayerControl;
    private final DropBombControl dropBombControl;
    private final Player myPlayer;

    public BombermanKeyAdapter(Player game) {
        this.myPlayer = game;
        ControlFactory instance = ControlFactory.instance();
        movePlayerControl = instance.createClientToServerControl(MovePlayerControl.class);
        dropBombControl = instance.createClientToServerControl(DropBombControl.class);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Character.toLowerCase(e.getKeyChar())) {
            case 'w' -> movePlayerControl.movePlayer(myPlayer.getName(), Direction.UP);
            case 'a' -> movePlayerControl.movePlayer(myPlayer.getName(), Direction.LEFT);
            case 's' -> movePlayerControl.movePlayer(myPlayer.getName(), Direction.DOWN);
            case 'd' -> movePlayerControl.movePlayer(myPlayer.getName(), Direction.RIGHT);
            case ' ' -> dropBombControl.dropBomb(myPlayer.getName(), myPlayer.getX(), myPlayer.getY());
        }
    }
}
