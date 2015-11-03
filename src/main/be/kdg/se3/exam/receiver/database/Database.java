package be.kdg.se3.exam.receiver.database;

import be.kdg.se3.exam.receiver.broker.InputChannelException;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.database
 */
public interface Database {
    void onInsert(String s);
    void onError(InputChannelException exception);
}
