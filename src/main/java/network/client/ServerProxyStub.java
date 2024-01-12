package network.client;

import network.Message;
import protocol.client2server.DropBomb;
import protocol.client2server.JoinGame;
import protocol.client2server.MovePlayer;
import protocol.server2client.BombDropped;
import protocol.server2client.PlayerJoined;
import protocol.server2client.PlayerMoved;
import protocol.server2client.StartGame;

import java.util.Random;
import java.util.UUID;

/**
 * Die Klasse ServerProxyStub ist von ServerProxy abgeleitet und dient dazu den Client
 * isoliert von der Netzwerkschicht testen zu k�nnen. Hierzu muss der Client ein Objekt
 * von dieser Klasse erzeugen. Er kann dann die send-Methode aufrufen. Damit kann das
 * Senden einer Meldung an den Server simuliert werden. Die Meldung wird lediglich auf
 * der Konsole ausgegeben. Die send-Methode kann den Testbed�rfnissen entsprechend
 * angepasst werden. Insbesondere k�nnen aus der send-Methode heraus Antworten vom
 * Server simuliert werden. Hierzu steht die Methode deliverResponseMessageToClient
 * zur Verf�gung. Diese liefert eine beliebige Meldung an den Client aus und ben�tzt
 * daf�r einen separaten Thread, so wie dies auch mit der richtigen Netzwerkschicht
 * der Fall sein wird.
 *
 * @author Andres Scheidegger
 */
public class ServerProxyStub extends ServerProxy {

    /**
     * Konstruktor. Muss vom Client aufgerufen werden.
     *
     * @param clientApplication Eine Referenz auf ein Objekt des Clients, welches das
     *                          Interface ClientApplicationInterface implementiert.
     */
    public ServerProxyStub(ClientApplicationInterface clientApplication) {
        super(clientApplication);
    }

    /**
     * Kann vom Client aufgerufen werden, um den Versand einer Meldung zu simulieren.
     * Kann den Testbed�rfnissen entsprechend angepasst werden.
     *
     * @see ServerProxy#send(Message)
     */
    @Override
    public void send(Message message) {
        System.out.println(message);
        if (message instanceof JoinGame joinGame) {
            String name = joinGame.getPlayerName();

            // Hier kommt die Antwort. Z.B.:
            PlayerJoined playerJoinedMessage = new PlayerJoined("Donald Duck", 2, 3);
            PlayerJoined playerJoinedMessage2 = new PlayerJoined("Donald Duck2", 3, 0);
            PlayerJoined playerJoinedMessage3 = new PlayerJoined("Donald Duck3", 1, 1);
            deliverResponseMessageToClient(playerJoinedMessage);
            deliverResponseMessageToClient(playerJoinedMessage2);
            deliverResponseMessageToClient(playerJoinedMessage3);
            deliverResponseMessageToClient(new PlayerJoined(name, 1, 2));

            char[][] initialGameState = {
                    {'i', 'i', 'i', 'i', 'i'},
                    {'i', '2', '0', 'f', 'i'},
                    {'i', 'f', 'i', '3', 'i'},
                    {'1', 'f', 'f', 'f', 'i'},
                    {'i', 'i', 'i', 'i', 'i'}};
            StartGame startGame = new StartGame(initialGameState);
            deliverResponseMessageToClient(startGame);
        }

        if(message instanceof MovePlayer movePlayer){
            System.out.println(movePlayer.getPlayerName() + " moved to: " + movePlayer.getDirection().name());
            PlayerMoved playerMoved = new PlayerMoved(movePlayer.getPlayerName(), movePlayer.getDirection());
            deliverResponseMessageToClient(playerMoved);
        }

        if(message instanceof DropBomb dropBomb){
            BombDropped bombDropped = new BombDropped(UUID.randomUUID().toString(), dropBomb.getPositionX(), dropBomb.getPositionY());
            deliverResponseMessageToClient(bombDropped);
        }
    }

    /**
     * Simuliert den Empfang einer Meldeung vom Server �ber das Netzwerk.
     *
     * @param responseMessage Das Meldungsobjekt, welches an den Client ausgeliefert
     *                        werden soll.
     */
    private void deliverResponseMessageToClient(Message responseMessage) {
        Thread responseThread = new Thread() {
            @Override
            public void run() {
                clientApplication.handleMessage(responseMessage);
            }
        };
        responseThread.start();
    }
}
