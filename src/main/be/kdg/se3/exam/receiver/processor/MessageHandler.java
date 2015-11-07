package be.kdg.se3.exam.receiver.processor;

/**
 * Created by   Shenno Willaert
 * Date         7/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Interface to handle incoming message from a message broker.
 */
public interface MessageHandler {
    /**
     * Handle the incoming message
     * @param message the message coming from the message broker
     */
    void handleMessage(String message);
}
