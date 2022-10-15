package br.edu.ifpb.project.quiz.jogo.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMq {
    private final String NAME_QUEUE = "QUEUE_";
    private Map<String, String> argsConnection = new HashMap<>();
    private ConnectionFactory factory = new ConnectionFactory();

    /* [0] => HOST, [1] => USERNAME, [2] => PASSWORD */

    public RabbitMq() {
        this.argsConnection.put("HOST", "localhost");
        this.argsConnection.put("USERNAME", "mqadmin");
        this.argsConnection.put("PASSWORD", "Admin123XX_");

        this.factory.setHost(this.argsConnection.get("HOST"));
        this.factory.setUsername(this.argsConnection.get("USERNAME"));
        this.factory.setPassword(this.argsConnection.get("PASSWORD"));
        
    }

    public void createQueueUser(String username) {
        try(
            Connection connection = this.factory.newConnection();
            Channel channel       = connection.createChannel();
        ) {
            channel.queueDeclare(NAME_QUEUE + username, false, true, true, null);
        }
        catch(IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
