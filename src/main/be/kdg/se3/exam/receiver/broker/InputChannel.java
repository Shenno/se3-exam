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
    void init();
    void startMonitoring();
    void stop();
}
