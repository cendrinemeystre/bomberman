package network.server;

import network.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ServerImplementation extends Server {
    private int port;
    final int DefaultPort = 8080;
    private List<ObjectOutputStream> clientOutStreams;

    public ServerImplementation(ServerApplicationInterface sa) {
        super(sa);
        this.port = DefaultPort;
        clientOutStreams = new ArrayList<>();
        SocketListener sl = new SocketListener(port, clientOutStreams, sa);
        sl.start();
    }

    @Override
    public void send(Message message, String connectionId) {
        try {
            clientOutStreams.get(Integer.parseInt(connectionId)).writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void broadcast(Message message) {
        for (ObjectOutputStream out : clientOutStreams) {
            try {
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
