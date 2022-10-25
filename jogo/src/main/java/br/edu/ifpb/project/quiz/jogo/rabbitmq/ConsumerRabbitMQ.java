package br.edu.ifpb.project.quiz.jogo.rabbitmq;

import java.util.StringTokenizer;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumerRabbitMQ {
    public static void main( String[] args ) throws Exception {

        final String NAME_QUEUE = "QUEUE_RESPONSE";
        final String URL_API    = "http://localhost:8080/api/jogo/registroQuestao";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("ifpb");
        factory.setPassword("ifpb");

        Connection connection  = factory.newConnection();
        Channel    channel     = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, true, false, false, null);

        DeliverCallback callback = (consumerTag, deliver) -> {
            String mensagem        = new String(deliver.getBody(), "UTF-8");
            StringTokenizer partes = new StringTokenizer(mensagem, ":");

            String email           = partes.nextToken();
            String idPergunta      = partes.nextToken();
            String idResposta      = partes.nextToken();

            try {
                
                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders  headers      = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                JSONObject registroJSON   = new JSONObject();
                registroJSON.put("email", email);
                registroJSON.put("idPergunta", idPergunta);
                registroJSON.put("idResposta", idResposta);

                HttpEntity<String> request = new HttpEntity<String>(registroJSON.toString(), headers);

                ResponseEntity    response = restTemplate.postForEntity(URL_API, request, Void.class);

                System.out.println(response.getBody());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(NAME_QUEUE, false, callback, consumerTag -> {});

    }

}