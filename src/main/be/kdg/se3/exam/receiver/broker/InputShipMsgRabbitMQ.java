package be.kdg.se3.exam.receiver.broker;

import be.kdg.se3.exam.receiver.database.Database;
import be.kdg.se3.exam.receiver.processor.ShipMessageHandler;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */

/**
 * Class that is going to monitor messages on queue using RabbitMQ
 */
public class InputShipMsgRabbitMQ implements InputChannel {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private Consumer consumer;
    private final String queue;
    private ShipMessageHandler shipMessageHandler;

    public InputShipMsgRabbitMQ(String queueName) {
        this.queue = queueName;
        shipMessageHandler = new ShipMessageHandler();
    }

    @Override
    public void init(){
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queue, false, false, false, null);
        } catch (Exception e) {

        }
    }

    @Override
    public void startMonitoring() {
        try {
        consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                shipMessageHandler.handleShipMessage(message);
            }
        };
        channel.basicConsume(queue, true, consumer);
        } catch (Exception e) {

        }
    }

    @Override
    public void stop() {
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {

        }
    }
}
