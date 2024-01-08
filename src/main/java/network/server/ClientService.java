package network.server;

import network.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientService extends Thread {
    private Socket clientSocket;
    private ServerApplicationInterface sa;
    private ObjectInputStream fromClient;
    private final String connectionId;

    public ClientService(Socket clientSocket, ServerApplicationInterface sa, String connectionId) {
        this.clientSocket = clientSocket;
        this.sa = sa;
        this.connectionId = connectionId;
        try {
            fromClient = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    Message msg = (Message) fromClient.readObject();
                    sa.handleMessage(msg, connectionId);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
