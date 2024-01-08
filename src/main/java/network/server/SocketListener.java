package network.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SocketListener extends Thread {

    private int port;
    private ServerSocket socket;
    private List<ObjectOutputStream> clientOutStreams;
    private ServerApplicationInterface sa;

    public SocketListener(int port, List<ObjectOutputStream> clientOutStreams, ServerApplicationInterface sa) {
        this.port = port;
        this.clientOutStreams = clientOutStreams;
        this.sa = sa;
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.println("Server started, listening on port " + port);

        while (true) {
            try {
                Socket clientSocket = socket.accept();
                clientOutStreams.add(new ObjectOutputStream(clientSocket.getOutputStream()));
                String connectionId = Integer.toString(clientOutStreams.size() - 1);
                new ClientService(clientSocket, sa, connectionId).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
