package be.kdg.se3.exam.receiver.broker;

import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DatabaseListener;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.processor.Buffer;
import com.rabbitmq.client.*;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */
public class InputRabbitMQ implements InputChannel {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private Consumer consumer;
    private final String QUEUE_NAME;
    private DatabaseListener database;

    public InputRabbitMQ(String queueName, DatabaseListener database) {
        this.QUEUE_NAME = queueName;
        this.database = database;
    }

    @Override
    public void init(){
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            //todo
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
                database.onInsert(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
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
