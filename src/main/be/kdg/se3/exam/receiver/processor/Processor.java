package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.ChannelException;
import be.kdg.se3.exam.receiver.broker.InputChannel;
import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Processor class that is the engine of the application.
 * Starts monitoring the message broker queue(s).
 */
public class Processor {
    private Collection<InputChannel> inputChannels;
    private final Logger logger = Logger.getLogger(this.getClass());

    public Processor() {
        inputChannels = new ArrayList<>();
    }

    public void addInputChannel(InputChannel inputChannel) {
        inputChannels.add(inputChannel);
    }

    public void start() {
        for (InputChannel inputChannel : inputChannels) {
            try {
                inputChannel.init();
                inputChannel.startMonitoring();
                logger.info(String.format("Started monitoring: %s",inputChannel.getInfo()));
            } catch (ChannelException e) {
                logger.fatal(e.getMessage(), e);
            }
        }
    }

    public void stop() {
        for (InputChannel inputChannel : inputChannels) {
            try {
                inputChannel.stop();
                logger.info(String.format("Stopped monitoring: %s",inputChannel.getInfo()));
            } catch (ChannelException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
