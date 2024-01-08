package application.server.network;

import protocol.client2server.ClientMessage;

public class MessageWrapper {
    private ClientMessage message;
    private String connectionId;

    public MessageWrapper(ClientMessage message, String connectionId) {
        this.message = message;
        this.connectionId = connectionId;
    }

    public ClientMessage getMessage() {
        return message;
    }

    public String getConnectionId() {
        return connectionId;
    }
}
