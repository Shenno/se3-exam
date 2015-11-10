package be.kdg.se3.exam.receiver.service;

/**
 * Created by   Shenno Willaert
 * Date         10/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.service
 */

/**
 * Class used to wrap exceptions from external service calls
 */
public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
