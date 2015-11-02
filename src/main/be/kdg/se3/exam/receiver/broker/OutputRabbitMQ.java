package be.kdg.se3.exam.receiver.broker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */
public class OutputRabbitMQ implements OutputChannel {
    private final String QUEUE_NAME;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public OutputRabbitMQ(String queue) {
        this.QUEUE_NAME = queue;
    }

    @Override
    public void init() {
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {

        }

    }

    @Override
    public void sendMessage(String message) {
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
