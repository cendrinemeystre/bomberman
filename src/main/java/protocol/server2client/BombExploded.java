package protocol.server2client;

import network.Message;

public record BombExploded(String id) implements Message {
}
