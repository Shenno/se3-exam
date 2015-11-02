/**
 * Created by   Shenno Willaert
 * Date         1/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;

public class Receive {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv)
            throws java.io.IOException,
            java.lang.InterruptedException {
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            ArrayList<String> list = new ArrayList<>();

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    list.add(message);
                    System.out.println(" [x] Received '" + message + "'");
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);
           // channel.close();
            //connection.close();

        }
        catch (Exception e)
        {

        }




    }
}
