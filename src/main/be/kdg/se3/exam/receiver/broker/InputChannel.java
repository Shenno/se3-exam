package be.kdg.se3.exam.receiver.broker;

/**
 * Created by   Shenno Willaert
 * Date         1/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */

/**
 * Interface for an inputchannel (messagebroker).
 */
public interface InputChannel {
    /**
     * Initialize the inputchannel.
     * @throws ChannelException
     */
    void init() throws ChannelException;

    /**
     * Start monitoring incoming messages.
     * @throws ChannelException
     */
    void startMonitoring() throws ChannelException;

    /**
     * Close the connections and shutdown the service.
     * @throws ChannelException
     */
    void stop() throws ChannelException;

    /**
     * Gives informations about the InputChannel
     * @return Short description about the channel
     */
    String getInfo();
}
