package be.kdg.se3.exam.receiver.broker;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */

/**
 * Class used to wrap exceptions coming from the broker.
 */
public class ChannelException extends Exception {
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }
}
