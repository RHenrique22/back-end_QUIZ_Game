package br.edu.ifpb.project.quiz.jogo.rabbitmq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.util.SerializationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;

import br.edu.ifpb.project.quiz.jogo.model.dto.RequestDTO;

public class RabbitMQ {

    private final String CONSUMER = "QUEUE_RESPONSE";
    private final String PRODUCER = "QUEUE_";
    private Map<String, String> argsConnection = new HashMap<>();
    private ConnectionFactory factory = new ConnectionFactory();

    public RabbitMQ() {
        this.argsConnection.put("HOST", "localhost");
        this.argsConnection.put("USERNAME", "ifpb");
        this.argsConnection.put("PASSWORD", "ifpb");

        this.factory.setHost(this.argsConnection.get("HOST"));
        this.factory.setUsername(this.argsConnection.get("USERNAME"));
        this.factory.setPassword(this.argsConnection.get("PASSWORD"));
        
    }

    public void publishInQueue(String username, String resposta) {
        try (
            Connection connection    = this.factory.newConnection();
            Channel    channel       = connection.createChannel();
        ) {

            channel.queueDeclare(this.PRODUCER + username, true, false, false, null);
            channel.basicPublish("", this.PRODUCER + username, MessageProperties.PERSISTENT_TEXT_PLAIN, resposta.getBytes());

        } catch(IOException | TimeoutException e) {
            e.printStackTrace();
        }
            
    }

    public RequestDTO consumerInQueue() {
        List<RequestDTO> result = new ArrayList<>();

        try(
            Connection connection    = this.factory.newConnection();
            Channel    channel       = connection.createChannel();
        ) {
            
            channel.queueDeclare(this.CONSUMER, true, false, false, null);

            do {
                DeliverCallback callback = (consumerTag, deliver) -> {
                    result.add((RequestDTO) SerializationUtils.deserialize(deliver.getBody()));
                    channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
                };

                channel.basicConsume(this.CONSUMER, false, callback, consumerTag -> {});

                if(!result.isEmpty()) {
                    return result.get(0);
                }

            } while(result.isEmpty());
        }
        catch(IOException | TimeoutException e) {
            e.printStackTrace();
        }

        return result.get(0);
    }

}
