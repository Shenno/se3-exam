package be.kdg.se3.exam.receiver.converter;

/**
 * Created by   Shenno Willaert
 * Date         8/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.converter
 */

/**
 * Class used to wrap exceptions occured while converting.
 */
public class ConvertException extends Exception {
    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
