package be.kdg.se3.exam.receiver.broker;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.broker
 */
public interface OutputChannel {
    void init();
    void sendMessage(String message);
    void stop();
}
