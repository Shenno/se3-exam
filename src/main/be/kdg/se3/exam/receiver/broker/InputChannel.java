package be.kdg.se3.exam.receiver.broker;

/**
 * Created by   Shenno Willaert
 * Date         1/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */

/**
 * Interface for an inputchannel (messagebroker)
 */
public interface InputChannel {
    /**
     * Initialize the inputchannel to use.
     */
    void init();

    /**
     * Start monitoring incoming messages.
     */
    void startMonitoring();

    /**
     * Close the connections and shutdown the service.
     */
    void stop();
}
