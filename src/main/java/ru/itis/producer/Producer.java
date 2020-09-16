package ru.itis.producer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.models.UserData;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {
//    private ObjectMapper objectMapper;
//    @Parameter(names={"--name"}, required = true)
//    private String name;
//    @Parameter(names={"--passport"}, required = true)
//    private String passport;
//    @Parameter(names={"--age"}, required = true)
//    private Integer age;
//    @Parameter(names={"--passportDate"}, required = true)
//    private String passportDate;
//
//    public static void main(String ... argv) {
//        Producer main = new Producer();
//        JCommander.newBuilder()
//                .addObject(main)
//                .build()
//                .parse(argv);
//        main.run();
//    }
    public static void main(String ... argv) {
        ObjectMapper objectMapper = new ObjectMapper();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            UserData userData = UserData.builder()
                    .name("name")
                    .passport("123")
                    .age(12)
                    .passportDate("13 september")
                    .build();
            String jsonData = objectMapper.writeValueAsString(userData);
            channel.basicPublish("letters_exchange", "", null, jsonData.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
