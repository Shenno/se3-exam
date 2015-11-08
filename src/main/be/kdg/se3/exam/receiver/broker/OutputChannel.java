package be.kdg.se3.exam.receiver.broker;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */

/**
 * Interface for an output messagechannel (broker)
 */
public interface OutputChannel {
    /**
     * Initialize the outputchannel.
     * @throws ChannelException
     */
    void init() throws ChannelException;

    /**
     * Send a message on the queue.
     * @param message
     * @throws ChannelException
     */
    void sendMessage(String message) throws ChannelException;

    /**
     * Shutdown the outputchannel.
     * @throws ChannelException
     */
    void stop() throws ChannelException;
}
