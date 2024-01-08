package network.client;

import network.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener extends Thread {

    private Socket socket;
    private final ClientApplicationInterface ca;
    private ObjectInputStream fromServer;

    public Listener(Socket socket, ClientApplicationInterface ca) {
        this.socket = socket;
        try {
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ca = ca;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Message msg = (Message) fromServer.readObject();
                ca.handleMessage(msg);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
