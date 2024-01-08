package application.server.network;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageQueueTest {

    @Test
    void append_WhenCalled_ShouldAddMessageToQueue() {
        // Arrange
        MessageQueue queue = new MessageQueue();
        MessageWrapper message = queue.remove();

        // Act
        queue.append(message);

        // Assert
        Assertions.assertEquals(1, queue.size());
        Assertions.assertEquals(message, queue.get(0));
    }

    @Test
    void remove_WhenCalledAndQueueIsEmpty_ShouldWaitUntilMessageArrives() throws InterruptedException {
        // Arrange
        MessageQueue queue = new MessageQueue();

        // Act
        MessageWrapper message = queue.remove();

        // Assert
        Assertions.assertNull(message);
    }

    @Test
    void remove_WhenCalledAndQueueIsNotEmpty_ShouldReturnFirstMessageInQueue() throws InterruptedException {
        // Arrange
        MessageQueue queue = new MessageQueue();
        MessageWrapper message = queue.remove();
        queue.append(message);

        // Act
        MessageWrapper returnedMessage = queue.remove();

        // Assert
        Assertions.assertEquals(message, returnedMessage);
    }
}
