package br.edu.ifpb.project.quiz.jogo.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.edu.ifpb.project.quiz.jogo.model.dto.RequestDTO;

public class ProducerRabbitMQ {

    private final String PRODUCER = "QUEUE_RESPONSE";
    private ConnectionFactory factory;

    public ProducerRabbitMQ() {
        this.factory = new ConnectionFactory();
        this.factory.setHost("localhost");
        this.factory.setUsername("ifpb");
        this.factory.setPassword("ifpb");
    }

    public void publishInQueue(RequestDTO response) {
        try (
            Connection connection     = this.factory.newConnection();
            Channel    channel        = connection.createChannel();
        ) {

            String mensagem = String.format("%s:%d:%d", response.getEmail(), response.getPergunta().getId(), response.getResposta().getId());

            channel.queueDeclare(this.PRODUCER, true, false, false, null);
            channel.basicPublish("", this.PRODUCER, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes());

        } catch(IOException | TimeoutException e) {
            e.printStackTrace();
        }
            
    }

}
